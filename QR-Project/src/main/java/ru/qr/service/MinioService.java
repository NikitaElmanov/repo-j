package ru.qr.service;

import java.io.InputStream;

public interface MinioService {

    void saveFile(InputStream inputStream, String fileName);

    InputStream getFile(String fileName);

}
