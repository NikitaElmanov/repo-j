package ru.web.app.model.dao.exception;

public class DAOException extends Exception {
    /**
     * Special exception for DAO layer with 1 param.
     * @param message
     */
    public DAOException(final String message) {
        super(message);
    }

    /**
     * Special exception for DAO layer with 2 params.
     * @param message
     * @param cause
     */
    public DAOException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
