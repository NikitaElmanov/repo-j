package ru.web.app.util;

import org.junit.Assert;
import org.junit.Test;

public class CryptoUtilTest {
    @Test
    public void checkEncryptingPassTrue() throws Exception {
        String pass = "test_pass";
        String pass2 = "test_pass";
        String first = CryptoUtil.byteArrayToHexString(CryptoUtil.computeHash(pass));
        String second = CryptoUtil.byteArrayToHexString(CryptoUtil.computeHash(pass2));
        Assert.assertEquals(first, second);
    }

    @Test
    public void checkEncryptingPassFalse() throws Exception {
        String pass = "test_pass";
        String pass2 = "another_pass";
        String first = CryptoUtil.byteArrayToHexString(CryptoUtil.computeHash(pass));
        String second = CryptoUtil.byteArrayToHexString(CryptoUtil.computeHash(pass2));
        Assert.assertNotEquals(first, second);
    }
}