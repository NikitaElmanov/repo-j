package ru.mongo.test.mongodbtest.quartz.mapper;

import org.mapstruct.Mapper;
import ru.mongo.test.mongodbtest.quartz.dto.JobEntityDto;
import ru.mongo.test.mongodbtest.quartz.model.JobEntity;

@Mapper(componentModel = "spring")
public interface JobEntityMapper {
    JobEntityDto toDto(JobEntity model);
}
