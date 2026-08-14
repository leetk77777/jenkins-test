package com.example.demo.batch.job;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.demo.batch.tasklet.ReportTasklet;

@Configuration
public class ReportJobConfig {

	@Bean
    public Step reportStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            ReportTasklet reportTasklet) {

        return new StepBuilder("reportStep", jobRepository)
                .tasklet(reportTasklet, transactionManager)
                .build();
    }

    @Bean
    public Job reportJob(
            JobRepository jobRepository,
            Step reportStep) {

        return new JobBuilder("reportJob", jobRepository)
                .start(reportStep)
                .build();
    }
}
