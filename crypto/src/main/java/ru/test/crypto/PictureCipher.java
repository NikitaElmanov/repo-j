package ru.test.crypto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Component
@RequiredArgsConstructor
public class PictureCipher {

    public static final String KEY_PASSWORD = "123qwe";
    public static final String CIPHER_TYPE = "AES";
    private final KeyStoreManager keyStoreManager;

    public byte[] encrypt(InputStream is) {
        try {
            Key key = keyStoreManager.getPKFromKS(KEY_PASSWORD);

            Cipher cipher = Cipher.getInstance(CIPHER_TYPE);

            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(is.readAllBytes());

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
                 InvalidKeyException | IOException e) {
            log.error(e.getMessage(), e);
        }

        return new byte[0];
    }

    public byte[] decrypt(byte[] data) {
        try {
            Key key = keyStoreManager.getPKFromKS(KEY_PASSWORD);

            Cipher cipher = Cipher.getInstance(CIPHER_TYPE);

            cipher.init(Cipher.DECRYPT_MODE, key);

            return cipher.doFinal(data);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
                 InvalidKeyException e) {
            log.error(e.getMessage(), e);
        }

        return new byte[0];
    }
}
