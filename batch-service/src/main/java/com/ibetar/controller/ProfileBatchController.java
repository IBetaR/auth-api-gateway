package com.ibetar.controller;

import com.ibetar.entity.ProfileVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("profiles")
@RequiredArgsConstructor
@Slf4j
public class ProfileBatchController {
    private final JobLauncher jobLauncher;
    private final Job runProfileDataJob;
    private final FlatFileItemReader<ProfileVO> profileVOReader;

    @PostMapping("upload-csv")
    public ResponseEntity<String> importProfileToDatabaseJob(
            @RequestBody String csvData
    ) {
        log.info("importing profile as csv Job started");
        try {
            // Set the CSV data as the resource for the FlatFileItemReader
            profileVOReader.setResource(new ByteArrayResource(csvData.getBytes()));

            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("csvData", csvData)
                    .addLong("startAt", System.currentTimeMillis())
                    .toJobParameters();

            JobExecution jobExecution = jobLauncher.run(runProfileDataJob, jobParameters);

            log.info("Job execution status: {}", jobExecution.getStatus());
            log.info("Job Instance: {}", jobExecution.getJobInstance());
            log.info("Job exit status: {}", jobExecution.getExitStatus());

            if (jobExecution.getStatus().isUnsuccessful()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to import profile data to the database. Job execution failed.");
            }

            log.info("Success Job exit status: {}", jobExecution.getExitStatus());
            return ResponseEntity.ok("CSV profiles data has been scheduled for import to the database.");
        } catch (Exception e) {
            log.error("Error importing profiles CSV data: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to import profiles CSV data to the database. Error: " + e.getMessage());
        }
    }
}