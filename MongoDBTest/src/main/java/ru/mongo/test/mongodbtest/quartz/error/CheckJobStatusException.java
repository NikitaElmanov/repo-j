package ru.mongo.test.mongodbtest.quartz.error;

public class CheckJobStatusException extends RuntimeException {
    public CheckJobStatusException(String message) {
        super(message);
    }
}
