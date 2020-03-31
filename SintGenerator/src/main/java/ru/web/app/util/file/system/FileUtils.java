package ru.web.app.util.file.system;

import java.io.*;

public class FileUtils {
    private static final int BYTES_DOWNLOAD = 1024;

    public static String createAndFillTMPFile(String script) throws IOException {

        File tempFile = File.createTempFile("tmp", null);
        FileOutputStream fos = new FileOutputStream(tempFile);
        byte[] buff = script.getBytes();
        fos.write(buff);

        return tempFile.getAbsoluteFile().toString();
    }

    public static void write (InputStream is, OutputStream os) throws IOException {
        int read;
        byte[] bytes = new byte[BYTES_DOWNLOAD];

        while((read = is.read(bytes))!= -1){
            os.write(bytes, 0, read);
        }
        os.flush();
        os.close();
    }
}
