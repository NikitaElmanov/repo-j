package ru.qr.web.exception;

public class QRCodeNotFoundException extends RuntimeException {
    public QRCodeNotFoundException(String message) {
        super(message);
    }
}
