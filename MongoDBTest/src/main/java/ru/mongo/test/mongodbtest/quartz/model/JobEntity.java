package ru.mongo.test.mongodbtest.quartz.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class JobEntity extends JobAbstract {
    @Id
    UUID id;
    TimerInfo timerInfo;
    JobStatus status;
}
