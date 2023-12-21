package ru.qr.service;

public interface QRManager {

    String encodeQR(byte[] data);

    byte[] decodeQR(String nameQRFile);

}
