package com.example.demo.batch.tasklet;

import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class SettlementTasklet implements Tasklet {

	@Override
    public RepeatStatus execute(
            StepContribution contribution,
            ChunkContext chunkContext) {

        System.out.println("===== settlementJob 정산 작업 실행 =====");

        return RepeatStatus.FINISHED;
    }
}
