package ru.web.app.dao.exception;

public class DAOException extends Exception {
    public DAOException(final String message) {
        super(message);
    }

    public DAOException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
