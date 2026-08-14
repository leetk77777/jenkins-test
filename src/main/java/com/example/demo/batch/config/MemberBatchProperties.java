package com.example.demo.batch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "batch.member")
public record MemberBatchProperties(
		int chunkSize,
        String schedule,
        boolean enabled
	) {

}
