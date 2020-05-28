package ru.web.app.util.file.system;

import org.junit.Test;

import java.io.IOException;

public class FileUtilsTest {

    @Test
    public void test1() throws IOException {
        String script = "dfddfdfdf sdsd\ndfdffdf\n\rdsdsfdssdadfss\tdff";

        String tempFile = FileUtils.createAndFillTMPFile(script);

        System.out.println(tempFile);
    }
}