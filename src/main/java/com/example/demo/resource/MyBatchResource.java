package com.example.demo.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public interface MyBatchResource {
	
	@GetMapping("/trigger")
	ResponseEntity<String> triggerBatchJob();
}