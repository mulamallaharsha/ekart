package com.codebag.ekart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckStatusController {
	
//	Kafka Example 
	/*
	 * @Autowired private KafkaTemplate<String, String> kafkaTemplate;
	 */

	@GetMapping("/checkStatus")
	public String checkStatus() {
//		kafkaTemplate.send("testtopic", "test data");
		return String.format("Running...!");
	}

	/*
	 * @KafkaListener(topics = "testtopic", id = "testid") public void
	 * testListner(String msg) { System.out.println(msg); }
	 */
}
