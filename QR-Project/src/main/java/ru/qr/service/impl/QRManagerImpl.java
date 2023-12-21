package ru.qr.service.impl;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.qr.service.QRManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

import static com.google.zxing.BarcodeFormat.QR_CODE;
import static java.nio.charset.StandardCharsets.UTF_8;
import static lombok.AccessLevel.PRIVATE;

@Slf4j
@Service
@FieldDefaults(level = PRIVATE)
public class QRManagerImpl implements QRManager {

    @Value("${dir.path:}")
    String dirPath;

    @Override
    public String encodeQR(byte[] data) {

        String qrFileName = makeQRFileName();
        String pathQRFile = makeFullPathQRFileToSave(qrFileName);

        try {

            BitMatrix matrix = new MultiFormatWriter()
                    .encode(new String(data, UTF_8), QR_CODE, 500, 500);
            MatrixToImageWriter.writeToPath(matrix, "png", Paths.get(pathQRFile));

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return qrFileName;
    }

    @Override
    public byte[] decodeQR(String qrFileName) {

        String pathQRFile = makeFullPathQRFileToSave(qrFileName);

        try {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(pathQRFile));

            BinaryBitmap bitmap = new BinaryBitmap(
                    new HybridBinarizer(
                            new BufferedImageLuminanceSource(bufferedImage)));

            Result result = new MultiFormatReader().decode(bitmap);

            ArrayList<byte[]> resultBytes = (ArrayList<byte[]>) result.getResultMetadata().get(ResultMetadataType.BYTE_SEGMENTS);

            if (CollectionUtils.isNotEmpty(resultBytes)) {
                return resultBytes.get(0);
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new byte[0];
    }

    private String makeFullPathQRFileToSave(String qrFileName) {
        return String.format("%s%s%s", dirPath, File.separator, qrFileName);
    }

    private String makeQRFileName() {
        return String.format("%s.%s", UUID.randomUUID(), "png");
    }
}
