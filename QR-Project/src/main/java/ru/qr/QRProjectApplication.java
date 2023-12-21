package ru.qr;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.qr.service.QRManager;

import java.nio.charset.StandardCharsets;

import static lombok.AccessLevel.PRIVATE;

@SpringBootApplication
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class QRProjectApplication implements CommandLineRunner {

    QRManager qrManager;

    public static void main(String[] args) {
        SpringApplication.run(QRProjectApplication.class, args);
    }

    @Override
    public void run(String... args) {
        String fileQRName = qrManager.encodeQR("https://mvnrepository.com/artifact/org.apache.commons/commons-lang3/3.14.0".getBytes(StandardCharsets.UTF_8));
        System.out.println(fileQRName);

        byte[] bytes = qrManager.decodeQR(fileQRName);
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
    }
}
