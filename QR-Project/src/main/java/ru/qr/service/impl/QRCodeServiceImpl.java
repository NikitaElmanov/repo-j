package ru.qr.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;
import ru.qr.model.QRCode;
import ru.qr.model.QRCodeContent;
import ru.qr.repository.QRCodeRepository;
import ru.qr.service.MinioService;
import ru.qr.service.QRCodeService;
import ru.qr.service.QRManager;
import ru.qr.web.api.CommonInfoDto;
import ru.qr.web.exception.QRCodeNotFoundException;
import ru.qr.web.mapper.QRCodeMapper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;
import static ru.qr.utils.Constants.QR_CODE_EXTENSION;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class QRCodeServiceImpl implements QRCodeService {

    QRManager qrManager;
    QRCodeMapper qrCodeMapper;
    MinioService minioService;
    QRCodeRepository qrCodeRepository;

    @Override
    public void save(QRCodeContent qrCodeContent) {
        String content = qrCodeContent.getContent();

        if (Objects.isNull(content)) {
            return;
        }

        // get QR code from String content
        byte[] bytes = qrManager.encodeQR(content.getBytes(StandardCharsets.UTF_8));

        // Save QR code Metadata in DB
        QRCode qrCode = new QRCode();
        qrCode.setSize(bytes.length);

        QRCode saveDQRCode = qrCodeRepository.save(qrCode);

        // Save QR code file in Minio
        String fileName = String.format("%s.%s", saveDQRCode.getId(), QR_CODE_EXTENSION);
        minioService.saveFile(new ByteArrayInputStream(bytes), fileName);
    }

    @Override
    public CommonInfoDto getOne(UUID id) {
        QRCode qrCode = qrCodeRepository.findById(id)
                .orElseThrow(() -> new QRCodeNotFoundException("QR Code no found by id: " + id));

        byte[] bytes = getAndDecodeData(id);

        return qrCodeMapper.mapConstruct(
                qrCode,
                new String(bytes, StandardCharsets.UTF_8));
    }

    @Override
    public List<CommonInfoDto> getAll() {
        List<QRCode> qrCodes = qrCodeRepository.findAll();
        List<CommonInfoDto> commonInfos = Lists.newArrayList();

        if (CollectionUtils.isEmpty(qrCodes)) {
            return Collections.emptyList();
        }

        for (QRCode qrCode : qrCodes) {
            byte[] qrCodeContent = getAndDecodeData(qrCode.getId());

            CommonInfoDto commonInfoDto = qrCodeMapper.mapConstruct(
                    qrCode,
                    new String(qrCodeContent, StandardCharsets.UTF_8));

            commonInfos.add(commonInfoDto);
        }

        return commonInfos;
    }

    @Override
    public InputStream getPicture(UUID id) {
        return getInputStreamFromStorage(id);
    }

    private byte[] getAndDecodeData(UUID id) {
        InputStream inputStream = getInputStreamFromStorage(id);

        return qrManager.decodeQR(inputStream);
    }

    private InputStream getInputStreamFromStorage(UUID id) {
        String fileName = String.format("%s.%s", id, QR_CODE_EXTENSION);
        return minioService.getFile(fileName);
    }

}
