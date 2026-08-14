package com.example.demo.batch.job;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.demo.batch.config.SettlementBatchProperties;
import com.example.demo.batch.tasklet.SettlementTasklet;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SettlementJobConfig {

	private final SettlementBatchProperties properties;
	
	@Bean
	public Step settlementStep(
	        JobRepository jobRepository,
	        PlatformTransactionManager transactionManager,
	        SettlementTasklet settlementTasklet) {

	    return new StepBuilder("settlementStep", jobRepository)
	            .tasklet(settlementTasklet, transactionManager)
	            .build();
	}

	@Bean
	public Job settlementJob(
	        JobRepository jobRepository,
	        Step settlementStep) {

	    return new JobBuilder("settlementJob", jobRepository)
	            .start(settlementStep)
	            .build();
	}
}
