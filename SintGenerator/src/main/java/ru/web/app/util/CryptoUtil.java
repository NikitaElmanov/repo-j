package ru.web.app.util;

public final class CryptoUtil {
    /**
     * returns bytes array (hash) from received string variable
     * by using java's security library.
     * specifically its class {@link java.security.MessageDigest}
     * @param x
     * @throws Exception
     */
    public static byte[] computeHash(final String x) throws Exception {
        java.security.MessageDigest d;
        d = java.security.MessageDigest.getInstance("SHA-1");
        d.reset();
        d.update(x.getBytes());
        return d.digest();
    }
    /**
     * returns string of hex figures from bytes array.
     * @param b
     */
    public static String byteArrayToHexString(final byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    private CryptoUtil() {
    }
}
