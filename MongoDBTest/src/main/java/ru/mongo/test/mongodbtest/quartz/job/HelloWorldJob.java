package ru.mongo.test.mongodbtest.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;
import ru.mongo.test.mongodbtest.quartz.model.JobEntity;

@Slf4j
@Component
public class HelloWorldJob implements Job {
    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        JobEntity job = (JobEntity) jobDataMap.get(this.getClass().getSimpleName());
        log.info("{} remainFireCount: {}", job.getTimerInfo().getCallbackData(), job.getTimerInfo().getRemainFireCount());
    }
}
