package ru.mongo.test.mongodbtest.quartz.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mongo.test.mongodbtest.quartz.dto.JobEntityDto;
import ru.mongo.test.mongodbtest.quartz.job.HelloWorldJob;
import ru.mongo.test.mongodbtest.quartz.mapper.JobEntityMapper;
import ru.mongo.test.mongodbtest.quartz.model.JobEntity;
import ru.mongo.test.mongodbtest.quartz.model.JobStatus;
import ru.mongo.test.mongodbtest.quartz.model.TimerInfo;
import ru.mongo.test.mongodbtest.quartz.repository.JobRepository;
import ru.mongo.test.mongodbtest.quartz.service.SchedulerService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/timer")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuartzController {

    SchedulerService schedulerService;
    JobRepository jobRepository;

    @PostMapping("/run")
    public void runJob() {
        final JobEntity job = initTimerInfo();
        schedulerService.scheduler(HelloWorldJob.class, job);
    }

    @DeleteMapping("/{timerId}")
    public boolean deleteJob(@PathVariable String timerId) {
        return schedulerService.deleteTimer(timerId);
    }

    @GetMapping
    public ResponseEntity<List<JobEntityDto>> getJobsInfo() {
        return ResponseEntity.ok(schedulerService.getJobsInfo());
    }

    private JobEntity initTimerInfo() {
        final TimerInfo timerInfo = new TimerInfo(
                5,
                5,
                false,
                3500,
                1000,
                "It's callback data!!!"
        );
        final JobEntity job = new JobEntity(UUID.randomUUID(),
                                      timerInfo,
                                      JobStatus.PENDING);
        jobRepository.saveJob(job);
        return job;
    }
}
