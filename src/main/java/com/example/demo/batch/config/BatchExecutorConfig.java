package com.example.demo.batch.config;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.JobOperatorFactoryBean;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BatchExecutorConfig {

	private final BatchExecutorProperties properties;
	
	@Bean(name = "batchTaskExecutor")
    public ThreadPoolTaskExecutor batchTaskExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(properties.corePoolSize());
        executor.setMaxPoolSize(properties.maxPoolSize());
        executor.setQueueCapacity(properties.queueCapacity());
        executor.setThreadNamePrefix("batch-job-");

        executor.initialize();

        return executor;
    }
	
	@Bean
    public JobOperator jobOperator(
            JobRepository jobRepository,
            JobRegistry jobRegistry,
            PlatformTransactionManager transactionManager,
            ThreadPoolTaskExecutor batchTaskExecutor) throws Exception {

        JobOperatorFactoryBean factory = new JobOperatorFactoryBean();

        factory.setJobRepository(jobRepository);
        factory.setJobRegistry(jobRegistry);
        factory.setTransactionManager(transactionManager);
        factory.setTaskExecutor(batchTaskExecutor);

        factory.afterPropertiesSet();

        return factory.getObject();
    }
}
