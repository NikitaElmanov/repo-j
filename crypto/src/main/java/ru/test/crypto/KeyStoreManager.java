package ru.test.crypto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.KeyStore;

@Slf4j
@Component
@RequiredArgsConstructor
public class KeyStoreManager {

    public static final String KEY_ALIAS = "keyAliasTest";
    public static final String KEYSTORE_PASS = "somepass";
    public static final String KEYSTORE_FILE_NAME = "keystore.p12";

    public void showContent(String password) {
        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");

            try(InputStream keyStoreData = getClass().getClassLoader().getResourceAsStream(KEYSTORE_FILE_NAME)){
                keyStore.load(keyStoreData, KEYSTORE_PASS.toCharArray());
            }

            KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection(password.toCharArray());
            keyStore.getEntry(KEY_ALIAS, entryPassword);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void store(String entryPasswordStr) {
        SecretKey secretKey = getSecretKey();

        if (secretKey == null) {
            return;
        }

        KeyStore.SecretKeyEntry secretKeyEntry = new KeyStore.SecretKeyEntry(secretKey);

        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");

            try(InputStream keyStoreData = getClass().getClassLoader().getResourceAsStream(KEYSTORE_FILE_NAME)){
                keyStore.load(keyStoreData, KEYSTORE_PASS.toCharArray());
            }

            KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection(entryPasswordStr.toCharArray());
            keyStore.setEntry(KEY_ALIAS, secretKeyEntry, entryPassword);

            try (FileOutputStream keyStoreOutputStream = new FileOutputStream("src/main/resources/keystore.p12")) {
                keyStore.store(keyStoreOutputStream, KEYSTORE_PASS.toCharArray());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private SecretKey getSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            return keyGenerator.generateKey();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
