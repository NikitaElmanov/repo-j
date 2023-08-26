package ru.mongo.test.mongodbtest.quartz.dto;

import ru.mongo.test.mongodbtest.quartz.model.JobStatus;
import ru.mongo.test.mongodbtest.quartz.model.TimerInfo;

public record JobEntityDto(TimerInfo timerInfo, JobStatus status) {}
