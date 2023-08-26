package ru.mongo.test.mongodbtest.quartz.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimerInfo {
    int fireCount;
    int remainFireCount;
    boolean runForever;
    long repeatIntervalMs;
    long initialDelayMs;
    String callbackData;
}
