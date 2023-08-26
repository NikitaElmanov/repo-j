package ru.mongo.test.mongodbtest.quartz.util;

import lombok.NoArgsConstructor;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import ru.mongo.test.mongodbtest.quartz.model.JobEntity;

import java.util.Date;

@NoArgsConstructor
public class TimerUtils {

    public static JobDetail buildJobDetail(final Class<? extends Job> jobClass, final JobEntity job) {
        final JobDataMap jobDataMap = new JobDataMap(){{
            put(jobClass.getSimpleName(), job);
        }};

        return JobBuilder
                .newJob(jobClass)
                .withIdentity(jobClass.getSimpleName())
                .usingJobData(jobDataMap)
                .build();
    }

    public static Trigger buildTrigger(final Class<? extends Job> jobClass, final JobEntity job) {
        SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInMilliseconds(job.getTimerInfo().getRepeatIntervalMs());

        builder = job.getTimerInfo().isRunForever() ?
                builder.repeatForever() : builder.withRepeatCount(job.getTimerInfo().getFireCount() - 1);

        return TriggerBuilder.newTrigger()
                .withIdentity(jobClass.getSimpleName())
                .withSchedule(builder)
                .startAt(new Date(System.currentTimeMillis() + job.getTimerInfo().getInitialDelayMs()))
                .build();
    }
}
