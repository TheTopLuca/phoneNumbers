package com.PhoneNumbers.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.PhoneNumbers.springboot")
public class PhoneNumbersApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PhoneNumbersApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

	}

}
