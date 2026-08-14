package com.example.demo.batch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "batch.executor")
public record BatchExecutorProperties(
		int corePoolSize,
        int maxPoolSize,
        int queueCapacity
    ) {

}
