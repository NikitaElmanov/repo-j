package ru.mongo.test.mongodbtest.quartz.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Service;
import ru.mongo.test.mongodbtest.quartz.dto.JobEntityDto;
import ru.mongo.test.mongodbtest.quartz.mapper.JobEntityMapper;
import ru.mongo.test.mongodbtest.quartz.model.JobEntity;
import ru.mongo.test.mongodbtest.quartz.model.JobStatus;
import ru.mongo.test.mongodbtest.quartz.model.TimerInfo;
import ru.mongo.test.mongodbtest.quartz.repository.JobRepository;
import ru.mongo.test.mongodbtest.quartz.util.TimerUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SchedulerService {

    Scheduler scheduler;
    JobRepository jobRepository;
    JobEntityMapper mapper;

    @PostConstruct
    public void init() {
        try {
            scheduler.getListenerManager().addTriggerListener(new SimpleTriggerListener(this, jobRepository));
            scheduler.start();
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void scheduler(final Class<? extends Job> jobClass, final JobEntity job) {
        final JobDetail jobDetail = TimerUtils.buildJobDetail(jobClass, job);
        final Trigger trigger = TimerUtils.buildTrigger(jobClass, job);

        try {
            scheduler.scheduleJob(jobDetail, trigger);
            jobRepository.updateJobStatus(job.getId(), JobStatus.RUNNING);
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void updateTimer(final String timerId, final TimerInfo timerInfo) {
        try {
            final JobDetail jobDetail = scheduler.getJobDetail(new JobKey(timerId));

            if (jobDetail == null) {
                log.error(String.format("%s, jobDetail is null by id = %s", this.getClass().getSimpleName(), timerId));
                return;
            }

            jobDetail.getJobDataMap().put(timerId, timerInfo);
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
    }

    public boolean deleteTimer(final String timerId) {
        try {
            JobDetail jobDetail = scheduler.getJobDetail(new JobKey(timerId));
            JobEntity job = (JobEntity) jobDetail.getJobDataMap().get(timerId);

            if (job == null) {
                return false;
            }

            jobRepository.updateJobStatus(job.getId(), JobStatus.STOPPED);

            scheduler.deleteJob(new JobKey(timerId));
            return true;
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    @PreDestroy
    public void preDestroy() {
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
    }

    public List<JobEntityDto> getJobsInfo() {
        try {
            List<JobEntityDto> jobDtos = scheduler.getJobKeys(GroupMatcher.anyGroup()).stream()
                    .map(jobKey -> {
                        try {
                            var jobDetail = scheduler.getJobDetail(jobKey);
                            var job = (JobEntity) jobDetail.getJobDataMap().get(jobKey.getName());
                            return job;
                        } catch (SchedulerException e) {
                            log.error(e.getMessage(), e);
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .map(mapper::toDto)
                    .collect(Collectors.toList());

            if (jobDtos.isEmpty()) {
                return jobRepository.getAllJobs().stream()
                        .map(mapper::toDto)
                        .collect(Collectors.toList());
            } else {
                return jobDtos;
            }
        } catch (SchedulerException e) {
            log.error(e.getMessage(), e);
        }
        return Collections.EMPTY_LIST;
    }
}
