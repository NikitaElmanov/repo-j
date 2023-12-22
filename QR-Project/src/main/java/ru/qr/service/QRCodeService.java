package ru.qr.service;

import org.springframework.web.multipart.MultipartFile;
import ru.qr.model.QRCodeContent;
import ru.qr.web.api.CommonInfoDto;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public interface QRCodeService {

    CommonInfoDto getOne(UUID id);

    void save(QRCodeContent qrCodeContent);

    List<CommonInfoDto> getAll();

    InputStream getPicture(UUID id);

    String getContentByPicture(MultipartFile picture);
}
