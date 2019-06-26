package ru.pro.filters;

import javax.servlet.ServletOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class FilterServletOutputStream extends ServletOutputStream {
    private DataOutputStream stream;

    public FilterServletOutputStream(ByteArrayOutputStream output) {
    }

    public void write(int b) throws IOException {
        stream.write(b);
    }
}
