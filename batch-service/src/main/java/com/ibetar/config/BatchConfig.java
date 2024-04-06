package com.ibetar.config;

import com.ibetar.entity.UserVO;
import com.ibetar.repository.UserVORepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
@Slf4j
public class BatchConfig {

//    private final PasswordEncoder passwordEncoder;
    private final UserVORepository userVORepository;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public FlatFileItemReader<UserVO> userVOReader() {
        log.debug("FlatFileItemReader userVOReader started..");
        return new FlatFileItemReaderBuilder<UserVO>()
                .name("readerStep")
                .resource(new ClassPathResource("users.csv"))
                .linesToSkip(1)
                .delimited()
                .delimiter(",")
                .names(
                        "id",
                        "name",
                        "username",
                        "email",
                        "roles",
                        "profileImageId",
                        "isEnabled",
                        "lastLogin"
                )
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(UserVO.class);
                }})
                .build();
    }

    @Bean
    public UserVOProcessor userVOProcessor() {
        return new UserVOProcessor();
    }

    @Bean
    public RepositoryItemWriter<UserVO> userVOWriter() {
        RepositoryItemWriter<UserVO> writer = new RepositoryItemWriter<>();
        writer.setRepository(userVORepository);
        writer.setMethodName("save");

        log.debug("Repository Item Writer ok: {}", writer);
        return writer;
    }

    @Bean
    public Step importUserDataStep() {
        log.debug("Importing User Data Step...");
        return new StepBuilder("importUserDataStep", jobRepository)
                .<UserVO, UserVO>chunk(1000, transactionManager)
                .reader(userVOReader())
                .processor(userVOProcessor())
                .writer(userVOWriter())
                .taskExecutor( taskExecutor())
                .build();
    }

    @Bean
    public Job runUserDataJob() {
        return new JobBuilder("runUserDataJob", jobRepository)
                .start(importUserDataStep())
                .build();
    }
    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(3);
        return asyncTaskExecutor;
    }

}