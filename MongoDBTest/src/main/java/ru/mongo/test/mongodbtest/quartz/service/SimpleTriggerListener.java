package ru.mongo.test.mongodbtest.quartz.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;
import ru.mongo.test.mongodbtest.quartz.model.JobEntity;
import ru.mongo.test.mongodbtest.quartz.model.JobStatus;
import ru.mongo.test.mongodbtest.quartz.model.TimerInfo;
import ru.mongo.test.mongodbtest.quartz.repository.JobRepository;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SimpleTriggerListener implements TriggerListener {

    SchedulerService schedulerService;

    JobRepository jobRepository;

    public SimpleTriggerListener(SchedulerService schedulerService, JobRepository jobRepository) {
        this.schedulerService = schedulerService;
        this.jobRepository = jobRepository;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        final String triggerId = trigger.getKey().getName();
        final JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        final JobEntity job = (JobEntity) jobDataMap.get(triggerId);
        final TimerInfo timerInfo = job.getTimerInfo();

        if (!timerInfo.isRunForever()) {
            final int remainFireCount = timerInfo.getRemainFireCount();
            if (remainFireCount == 0) {
                return;
            }
            if (remainFireCount == 1) { //TODO so bad approach but i don't care now)
                jobRepository.updateJobStatus(job.getId(), JobStatus.FINISHED);
            }
            timerInfo.setRemainFireCount(remainFireCount - 1);
        }

        schedulerService.updateTimer(triggerId, timerInfo);
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {

    }

    @Override
    public void triggerComplete(Trigger trigger,
                                JobExecutionContext context,
                                Trigger.CompletedExecutionInstruction triggerInstructionCode) {

    }
}
