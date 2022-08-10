package com.codebag.ekart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
//@EnableScheduling
public class EkartApplication {

	public static void main(String[] args) {
		SpringApplication.run(EkartApplication.class, args);
	}

	/*
	 * @Scheduled(cron = "* * * * * *") public void cronJobSch() throws Exception {
	 * System.out.println("Cron running every second"); }
	 */

}
