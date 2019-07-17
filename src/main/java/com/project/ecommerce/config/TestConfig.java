package com.project.ecommerce.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.project.ecommerce.services.EmailService;
import com.project.ecommerce.services.impl.DBServiceImpl;
import com.project.ecommerce.services.impl.MockEmailServiceImpl;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	DBServiceImpl dbService;

	@Bean
	public boolean instanceDatabase() throws ParseException {
		this.dbService.dataBaseTest();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new MockEmailServiceImpl();
	}
}
