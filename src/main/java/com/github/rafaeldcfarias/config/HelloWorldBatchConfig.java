package com.github.rafaeldcfarias.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
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
		return jobBFactory.get("job1").start(step1()).build();
	}

	public Step step1() {
		return stepBFactory.get("step1").tasklet(new Tasklet() {

			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				System.out.println("Step 1!");
				return RepeatStatus.FINISHED;
			}
		}).build();
	}

}
