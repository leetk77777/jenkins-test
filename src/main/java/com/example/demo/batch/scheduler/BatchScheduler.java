package com.example.demo.batch.scheduler;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.batch.config.MemberBatchProperties;
import com.example.demo.batch.config.ReportBatchProperties;
import com.example.demo.batch.config.SettlementBatchProperties;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BatchScheduler {

	private final JobOperator jobOperator;
	
    private final Job memberJob;
    private final Job settlementJob;
    private final Job reportJob;
    
    private final MemberBatchProperties properties;
    private final SettlementBatchProperties settlementProperties;
    private final ReportBatchProperties reportProperties;

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
    
    @Scheduled(cron = "${batch.settlement.schedule}")
    public void runSettlementJob() throws Exception {

        if (!settlementProperties.enabled()) {
            return;
        }

        JobParameters parameters = new JobParametersBuilder()
                .addLong("runTime", System.currentTimeMillis())
                .toJobParameters();

        jobOperator.start(settlementJob, parameters);
    }
    
    @Scheduled(cron = "${batch.report.schedule}")
    public void runReportJob() throws Exception {

        if (!reportProperties.enabled()) {
            return;
        }

        JobParameters parameters = new JobParametersBuilder()
                .addLong("runTime", System.currentTimeMillis())
                .toJobParameters();

        jobOperator.start(reportJob, parameters);
    }
}
