package com.ram.feedback_form;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FeedbackFormApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedbackFormApplication.class, args);
	}

}
