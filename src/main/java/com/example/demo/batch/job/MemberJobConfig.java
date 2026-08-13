package com.example.demo.batch.job;

import com.example.demo.batch.domain.Member;
import com.example.demo.batch.processor.MemberItemProcessor;

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
public class MemberJobConfig {

	@Bean
    public Step memberStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            JdbcCursorItemReader<Member> memberReader,
            MemberItemProcessor memberItemProcessor,
            JdbcBatchItemWriter<Member> memberWriter) {

        return new StepBuilder("memberStep", jobRepository)
                .<Member, Member>chunk(3)
                .transactionManager(transactionManager)
                .reader(memberReader)
                .processor(memberItemProcessor)
                .writer(memberWriter)
                .build();
    }

    @Bean
    public Job memberJob(
            JobRepository jobRepository,
            Step memberStep) {

        return new JobBuilder("memberJob", jobRepository)
                .start(memberStep)
                .build();
    }
}
