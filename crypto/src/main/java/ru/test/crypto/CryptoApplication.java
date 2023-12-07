package ru.test.crypto;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static java.nio.charset.StandardCharsets.UTF_8;

@SpringBootApplication
@RequiredArgsConstructor
public class CryptoApplication {

//    private final CryptoTest cryptoTest;
    private final KeyStoreManager keyStoreManager;
    private final PictureCipher pictureCipher;

    public static void main(String[] args) {
        SpringApplication.run(CryptoApplication.class, args);
    }

//    @PostConstruct
    private void init() {
//        String entryPasswordStr = "123qwe";
//        keyStoreManager.showContent(entryPasswordStr);
//        keyStoreManager.store(entryPasswordStr);
//        keyStoreManager.showContent(entryPasswordStr);

//        // 1
//        String initStr = "asfddsgfkdfjs123123fsdfsdf";
//        byte[] data = initStr.getBytes(UTF_8);
//        boolean result = cryptoTest.signAndVerifyData(data);
//        System.out.println(result);
//
//        // 2
//        byte[] data2 = initStr.getBytes(UTF_8);
//        byte[] bytes = cryptoTest.encryptAndDecryptData(data2);
//        String resultStr = new String(bytes, UTF_8);
//        System.out.println(initStr.equals(resultStr));
//
//        // 3
//        String initStr2 = "asdasdw34234bdfgdfg";
//        byte[] data1 = initStr.getBytes(UTF_8);
//        data2 = initStr2.getBytes(UTF_8);
//        bytes = cryptoTest.encryptAndDecryptData2(data1, data2);
//        resultStr = new String(bytes, UTF_8).trim();
//        System.out.println((initStr + initStr2).equals(resultStr));
//
//        // 4
//        byte[] hash1 = cryptoTest.digestData2(initStr.getBytes(UTF_8), initStr2.getBytes(UTF_8), "MD2");
//        byte[] hash2 = cryptoTest.digestData2(initStr.getBytes(UTF_8), initStr2.getBytes(UTF_8), "MD5");
//        byte[] hash3 = cryptoTest.digestData2(initStr.getBytes(UTF_8), initStr2.getBytes(UTF_8), "SHA-512");
//        String resultStr1 = new String(hash1, UTF_8);
//        String resultStr2 = new String(hash2, UTF_8);
//        String resultStr3 = new String(hash3, UTF_8);
//        System.out.println(resultStr1 + "\n\r" + resultStr2 + "\n\r" + resultStr3);
//
//        // 5
//        byte[] bytes1 = cryptoTest.macData(initStr.getBytes(UTF_8));
//        System.out.println(bytes1);
    }

//    @PostConstruct
//    private void cipherPicture() throws IOException {
//        InputStream is = getClass().getClassLoader().getResourceAsStream("test.jpg");
//
//        byte[] encryptedBytes = pictureCipher.encrypt(is);
//
//        Files.write(new File("C:\\Users\\Nikita.Elmanov\\IdeaProjects\\repo-j\\crypto\\src\\main\\resources\\testEncrypted.jpg").toPath(), encryptedBytes);
//
//        is = getClass().getClassLoader().getResourceAsStream("testEncrypted.jpg");
//        byte[] data2 = is.readAllBytes();
//        byte[] result = pictureCipher.decrypt(data2);
//
//        Files.write(new File("C:\\Users\\Nikita.Elmanov\\IdeaProjects\\repo-j\\crypto\\src\\main\\resources\\test3.jpg").toPath(), result);
//    }
}
