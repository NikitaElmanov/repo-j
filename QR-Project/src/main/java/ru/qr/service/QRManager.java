package ru.qr.service;

import java.io.InputStream;

public interface QRManager {

    byte[] encodeQR(byte[] data);

    byte[] decodeQR(InputStream inputStream);

}
