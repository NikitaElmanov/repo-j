package ru.test.crypto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;

@Slf4j
@Component
public class CryptoTest {

    public boolean signAndVerifyData(byte[] data) {
        SecureRandom secureRandom = new SecureRandom();
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.genKeyPair();

            Signature signature = Signature.getInstance("SHA256WithDSA");
            signature.initSign(keyPair.getPrivate(), secureRandom);

            signature.update(data);

            byte[] sign = signature.sign();

            Signature signature2 = Signature.getInstance("SHA256WithDSA");
            signature2.initVerify(keyPair.getPublic());

            signature2.update(data);

            return signature2.verify(sign);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            log.error(e.getMessage(), e);
        }

        return false;
    }

    public byte[] encryptAndDecryptData(byte[] data) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = new SecureRandom();
            int keyBitSize = 256;
            keyGenerator.init(keyBitSize, secureRandom);

            SecretKey secretKey = keyGenerator.generateKey();

            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] bytes = cipher.doFinal(data);

            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            return cipher.doFinal(bytes);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    public byte[] encryptAndDecryptData2(byte[] data, byte[] data2) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = new SecureRandom();
            int keyBitSize = 256;
            keyGenerator.init(keyBitSize, secureRandom);

            SecretKey secretKey = keyGenerator.generateKey();

            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] bytes = cipher.update(data);
            byte[] bytes2 = cipher.doFinal(data2);

            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(bytes);
            outputStream.write(bytes2);
            byte[] fullData = outputStream.toByteArray();

            byte[] result = new byte[1024];
            cipher.doFinal(fullData, 0, fullData.length, result);

            return result;

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | IOException | ShortBufferException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    public byte[] digestData2(byte[] data1, byte[] data2, String alg) {
        try {
            MessageDigest md = MessageDigest.getInstance(alg);

            md.update(data1);
            md.update(data2);

            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    public byte[] macData(byte[] data) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");

            SecureRandom secureRandom = new SecureRandom();
            int keyBitSize = 256;

            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(keyBitSize, secureRandom);

            SecretKey secretKey = keyGenerator.generateKey();

            mac.init(secretKey);

            return mac.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
