package ru.mongo.test.mongodbtest.quartz.repository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ru.mongo.test.mongodbtest.quartz.error.CheckJobStatusException;
import ru.mongo.test.mongodbtest.quartz.model.JobEntity;
import ru.mongo.test.mongodbtest.quartz.model.JobStatus;

import java.util.List;
import java.util.UUID;

@Slf4j
@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JobRepository {

    MongoTemplate mongoTemplate;

    public void updateJobStatus(UUID jobId, JobStatus jobStatus) {
        List<JobEntity> jobs = mongoTemplate.find(Query.query(Criteria.where("_id").is(jobId)), JobEntity.class);
        if (jobs.isEmpty()) {
            log.error("job is null, did not find any jobs(((");
            return;
        }

        JobEntity job = jobs.get(0);
        JobStatus status = job.getStatus();

        switch (status) {
            case RUNNING, PENDING -> job.setStatus(jobStatus);
            default -> throw new CheckJobStatusException("job's status is wrong value " + status);
        }

        saveJob(job);
    }

    public void saveJob(final JobEntity job) {
        mongoTemplate.save(job);
    }

    public List<JobEntity> getAllJobs() {
        return mongoTemplate.findAll(JobEntity.class);
    }
}
