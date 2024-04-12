package com.ibetar.config;

import com.ibetar.config.processor.ProfileVOProcessor;
import com.ibetar.entity.ProfileVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
@Slf4j
public class ProfileBatchConfig {
    private final MongoTemplate mongoTemplate;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public FlatFileItemReader<ProfileVO> profileVOReader() {
        return new FlatFileItemReaderBuilder<ProfileVO>()
                .name("profileVOReader")
                .resource(new ByteArrayResource("".getBytes()))
                .delimited()
                .names(
                        "id",
                        "name",
                        "email",
                        "profileImageId",
                        "kycVerified",
                        "legalType",
                        "identificationType",
                        "identificationNumber",
                        "country",
                        "pointsBalance",
                        "isActive",
                        "rating",
                        "isDeleted",
                        "createdDate",
                        "lastUpdateDate"
                )
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(ProfileVO.class);
                }})
                .build();
    }

    @Bean
    public Step importProfileStep() {
        log.debug("Importing Profile Data Step...");
        return new StepBuilder("importProfileDataStep", jobRepository)
                .<ProfileVO, ProfileVO>chunk(1000, transactionManager)
                .reader(profileVOReader())
                .processor(profileVOProcessor())
                .writer(profileVOWriter())
                .taskExecutor(profileTaskExecutor())
                .build();
    }

    @Primary
    @Bean
    public Job runProfileDataJob() {
        return new JobBuilder("runProfileDataJob", jobRepository)
                .start(importProfileStep())
                .build();
    }

    @Bean
    public ProfileVOProcessor profileVOProcessor() {
        return new ProfileVOProcessor();
    }

    @Bean
    public MongoItemWriter<ProfileVO> profileVOWriter() {
        MongoItemWriter<ProfileVO> writer = new MongoItemWriter<>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("profiles");
        return writer;
    }

    @Bean
    public TaskExecutor profileTaskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(3);
        return asyncTaskExecutor;
    }
}