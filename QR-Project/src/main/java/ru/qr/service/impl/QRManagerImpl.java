package ru.qr.service.impl;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import ru.qr.service.QRManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.UUID;

import static com.google.zxing.BarcodeFormat.QR_CODE;
import static java.nio.charset.StandardCharsets.UTF_8;
import static lombok.AccessLevel.PRIVATE;
import static ru.qr.utils.Constants.QR_CODE_EXTENSION;

@Slf4j
@Service
@FieldDefaults(level = PRIVATE)
public class QRManagerImpl implements QRManager {

    @Override
    public byte[] encodeQR(byte[] data) {
        try {
            BitMatrix matrix = new MultiFormatWriter()
                    .encode(new String(data, UTF_8), QR_CODE, 500, 500);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(matrix, QR_CODE_EXTENSION, outputStream);
            return outputStream.toByteArray();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new byte[0];
    }

    @Override
    public byte[] decodeQR(InputStream inputStream) {
        try {
            BufferedImage bufferedImage = ImageIO.read(inputStream);

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
}
