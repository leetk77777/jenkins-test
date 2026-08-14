package com.example.demo.batch.job;

import com.example.demo.batch.config.MemberBatchProperties;
import com.example.demo.batch.domain.Member;
import com.example.demo.batch.processor.MemberItemProcessor;
import com.example.demo.batch.tasklet.MemberSummaryTasklet;

import lombok.RequiredArgsConstructor;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.database.JdbcCursorItemReader;
import org.springframework.batch.infrastructure.item.database.JdbcBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class MemberJobConfig {
	
	private final MemberBatchProperties properties;

	@Bean
    public Step memberStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            JdbcCursorItemReader<Member> memberReader,
            MemberItemProcessor memberItemProcessor,
            JdbcBatchItemWriter<Member> memberWriter) {

        return new StepBuilder("memberStep", jobRepository)
                .<Member, Member>chunk(properties.chunkSize())
                .transactionManager(transactionManager)
                .reader(memberReader)
                .processor(memberItemProcessor)
                .writer(memberWriter)
                .build();
    }

    @Bean
    public Job memberJob(
            JobRepository jobRepository,
            Step memberStep,
            Step summaryStep) {

        return new JobBuilder("memberJob", jobRepository)
                .start(memberStep)
                .next(summaryStep)
                .build();
    }
    
    @Bean
    public Step summaryStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            MemberSummaryTasklet memberSummaryTasklet) {

        return new StepBuilder("summaryStep", jobRepository)
                .tasklet(memberSummaryTasklet, transactionManager)
                .build();
    }
}
