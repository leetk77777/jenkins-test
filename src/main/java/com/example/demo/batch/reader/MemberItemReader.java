package com.example.demo.batch.reader;

import javax.sql.DataSource;

import com.example.demo.batch.domain.Member;
import org.springframework.batch.infrastructure.item.database.JdbcCursorItemReader;
import org.springframework.batch.infrastructure.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.DataClassRowMapper;

@Configuration
public class MemberItemReader {

	@Bean
    public JdbcCursorItemReader<Member> memberReader(DataSource dataSource) {

        return new JdbcCursorItemReaderBuilder<Member>()
                .name("memberReader")
                .dataSource(dataSource)
                .sql("""
                     SELECT id, name, amount
                     FROM member
                     ORDER BY id
                     """)
                .rowMapper(new DataClassRowMapper<>(Member.class))
                .build();
    }
}
