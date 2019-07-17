package com.project.ecommerce.services.impl;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SMTPEmailServiceImpl extends AbstractEmailServiceImpl {
	
	private static final Logger LOG = LoggerFactory.getLogger(SMTPEmailServiceImpl.class);
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void enviarEmail(SimpleMailMessage message) {
		LOG.info("Enviando email...");
		this.mailSender.send(message);
		LOG.info("Email enviado com sucesso.");
	}

	@Override
	public void enviarHtmlEmail(MimeMessage message) {
		LOG.info("Enviando email...");
		this.javaMailSender.send(message);
		LOG.info("Email enviado com sucesso.");
	}

}
