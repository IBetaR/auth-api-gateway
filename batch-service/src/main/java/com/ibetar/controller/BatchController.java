package com.ibetar.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("batch-service")
@RequiredArgsConstructor
@Slf4j
public class BatchController {
    private final JobLauncher jobLauncher;
    private final Job job;

    @PostMapping
    public void importCsvToDatabaseJob() {
        log.info("importing Csv To Database Job started");
        JobParameters jobParameter = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();
        try {
            jobLauncher.run(job,jobParameter);
        } catch (JobExecutionAlreadyRunningException |
                JobRestartException |
                JobInstanceAlreadyCompleteException |
                JobParametersInvalidException e
        ) {
            log.error("error importing: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
