package com.example.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.demo.entity.User;

@Configuration
@EnableBatchProcessing
public class MyBatchConfiguration {

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private ItemReader<User> reader;

	@Autowired
	private ItemProcessor<User, User> processor;

	@Autowired
	private ItemWriter<User> writer;

	@Bean
	public Job importUserJob(PlatformTransactionManager transactionManager) {
		return new JobBuilder("importUserJob", jobRepository).incrementer(new RunIdIncrementer())
				.start(step1(transactionManager)).build();
	}

	@Bean
	public Step step1(PlatformTransactionManager transactionManager) {
		return new StepBuilder("step1", jobRepository).<User, User>chunk(10, transactionManager).reader(reader)
				.processor(processor).writer(writer).build();
	}
}