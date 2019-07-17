package com.project.ecommerce.services.impl;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailServiceImpl extends AbstractEmailServiceImpl {
	
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailServiceImpl.class);

	@Override
	public void enviarEmail(SimpleMailMessage message) {
		LOG.info("Simulando envio de email...");
		LOG.info(message.toString());
		LOG.info("Email enviado com sucesso.");
	}

	@Override
	public void enviarHtmlEmail(MimeMessage message) {
		LOG.info("Simulando envio de email HTML...");
		LOG.info(message.toString());
		LOG.info("Email enviado com sucesso.");
	}

}
