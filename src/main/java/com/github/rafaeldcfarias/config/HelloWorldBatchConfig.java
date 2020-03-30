package com.github.rafaeldcfarias.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class HelloWorldBatchConfig {

	@Autowired
	private JobBuilderFactory jobBFactory;

	@Autowired
	private StepBuilderFactory stepBFactory;

	@Bean
	public Job job1() {
		// @formatter:off
		return jobBFactory.get("job1").start(step1())
				.next(step2()).next(step2()).next(step3()).build();
		// @formatter:on
	}

	public Step step1() {
		return stepBFactory.get("step1").tasklet((contribution, chunkContext) -> {
			System.out.println("Step 1!");
			return RepeatStatus.FINISHED;
		}).build();
	}

	public Step step2() {
		return stepBFactory.get("step2").tasklet((contribution, chunkContext) -> {
			System.out.println("Step 2!");
			return RepeatStatus.FINISHED;
		}).build();
	}

	public Step step3() {
		return stepBFactory.get("step3").tasklet((contribution, chunkContext) -> {
			System.out.println("Step 3!");
			return RepeatStatus.FINISHED;
		}).build();
	}

}
