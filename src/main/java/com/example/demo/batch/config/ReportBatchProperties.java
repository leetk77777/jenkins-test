package com.example.demo.batch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "batch.report")
public record ReportBatchProperties(
		String schedule,
        boolean enabled
    ) {

}
