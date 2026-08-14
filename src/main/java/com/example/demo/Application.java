package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.demo.batch.config.BatchExecutorProperties;
import com.example.demo.batch.config.MemberBatchProperties;
import com.example.demo.batch.config.ReportBatchProperties;
import com.example.demo.batch.config.SettlementBatchProperties;

@EnableScheduling
@EnableConfigurationProperties({
	MemberBatchProperties.class,
	SettlementBatchProperties.class,
	ReportBatchProperties.class,
	BatchExecutorProperties.class
})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
