package com.example.demo.resource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyBatchJobResourceImpl implements MyBatchResource {
	
	@Autowired
	private Job job;
	
	@Autowired
	private JobLauncher jobLauncher;

	@Override
	public ResponseEntity<String> triggerBatchJob() {
		try {
            jobLauncher.run(job, new JobParametersBuilder().toJobParameters());
            return ResponseEntity.ok("Batch job has been invoked");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Failed to invoke batch job");
        }
	}
}