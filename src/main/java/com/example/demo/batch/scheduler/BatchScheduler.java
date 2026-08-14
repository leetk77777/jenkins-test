package com.example.demo.batch.scheduler;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.batch.config.MemberBatchProperties;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BatchScheduler {

	private final JobOperator jobOperator;
    private final Job memberJob;
    private final MemberBatchProperties properties;

    @Scheduled(cron = "${batch.member.schedule}")
    public void runMemberJob() throws Exception {
    	
    	if (!properties.enabled()) {
            return;
        }

        JobParameters parameters = new JobParametersBuilder()
                .addLong("runTime", System.currentTimeMillis())
                .toJobParameters(); 

        jobOperator.start(memberJob, parameters);
    }
}
