package com.example.demo.batch.tasklet;

import lombok.RequiredArgsConstructor;

import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberSummaryTasklet implements Tasklet {

	private final JdbcTemplate jdbcTemplate;

    @Override
    public RepeatStatus execute(
            StepContribution contribution,
            ChunkContext chunkContext) {

        jdbcTemplate.update("""
            INSERT INTO batch_summary (total_count, total_amount)
            SELECT COUNT(*), COALESCE(SUM(amount), 0)
            FROM member_result
            """);

        System.out.println("===== summaryStep 완료 =====");

        return RepeatStatus.FINISHED;
    }
}
