package com.project.ecommerce.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.project.ecommerce.services.EmailService;
import com.project.ecommerce.services.impl.DBServiceImpl;
import com.project.ecommerce.services.impl.SMTPEmailServiceImpl;

@Configuration
@Profile("prod")
public class ProdConfig {
	
	@Autowired
	private DBServiceImpl dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instanceDatabase() throws ParseException {
		
		if(!strategy.equals("create")) {
			return false;
		}
		
		this.dbService.dataBaseTest();
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		return new SMTPEmailServiceImpl();
	}
	
}
