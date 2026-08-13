package com.example.demo.batch.writer;

import javax.sql.DataSource;

import com.example.demo.batch.domain.Member;
import org.springframework.batch.infrastructure.item.database.JdbcBatchItemWriter;
import org.springframework.batch.infrastructure.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberItemWriter {

	@Bean
    public JdbcBatchItemWriter<Member> memberWriter(DataSource dataSource) {

        return new JdbcBatchItemWriterBuilder<Member>()
                .dataSource(dataSource)
                .sql("""
                    INSERT INTO member_result (id, name, amount)
                    VALUES (:id, :name, :amount)
                    """)
                .beanMapped()
                .build();
    }
}
