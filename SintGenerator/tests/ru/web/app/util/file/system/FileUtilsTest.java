package ru.web.app.util.file.system;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class FileUtilsTest {

    @Test
    public void test1() throws IOException {
        String script = "dfddfdfdf sdsd\ndfdffdf\n\rdsdsfdssdadfss\tdff";
        byte[] buff = script.getBytes();

        File tempFile = File.createTempFile("tmp", null);
        FileOutputStream fos = new FileOutputStream(tempFile);
        fos.write(buff);

        System.out.println(tempFile.getAbsoluteFile());
    }
}