package ru.web.app.util.file.system;

import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public final class FileUtils {
    /**
     * Initial size for bytes array.
     */
    private static final int BYTES_DOWNLOAD = 1024;

    /**
     * take String param and fill itself temp file.
     * @param script
     * @returns absolute path to tmp file.
     * @throws IOException
     */
    public static String createAndFillTMPFile(final String script)
                                                throws IOException {

        File tempFile = File.createTempFile("tmp", null);
        FileOutputStream fos = new FileOutputStream(tempFile);
        byte[] buff = script.getBytes();
        fos.write(buff);

        return tempFile.getAbsoluteFile().toString();
    }

    /**
     * take 2 params (InputStream and OutputStream)
     * and write first of them into second.
     * @param is
     * @param os
     * @throws IOException
     */
    public static void write(final InputStream is, final OutputStream os)
                                                      throws IOException {
        int read;
        byte[] bytes = new byte[BYTES_DOWNLOAD];

        while ((read = is.read(bytes)) != -1) {
            os.write(bytes, 0, read);
        }
        os.flush();
        os.close();
    }

    private FileUtils(){}
}
