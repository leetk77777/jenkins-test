package com.example.demo.batch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "batch.settlement")
public record SettlementBatchProperties(
		String schedule,
        boolean enabled
	) {

}
