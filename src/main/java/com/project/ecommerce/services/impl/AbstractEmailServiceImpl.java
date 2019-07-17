package com.project.ecommerce.services.impl;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.project.ecommerce.domain.Cliente;
import com.project.ecommerce.domain.Pedido;
import com.project.ecommerce.services.EmailService;

public abstract class AbstractEmailServiceImpl implements EmailService {

	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void enviarConfirmacaoEmail(Pedido pedido) {
		SimpleMailMessage message = this.getMessageSimpleMailMessage(pedido);
		this.enviarEmail(message);
	}

	protected SimpleMailMessage getMessageSimpleMailMessage(Pedido pedido) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(pedido.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Código: " + pedido.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(pedido.toString());
		return sm;
	}
	
	protected String getHtmlEmail(Pedido obj) {
		Context context = new Context();
		context.setVariable("pedido", obj);
		return templateEngine.process("email/confirmacaoPedido", context);
	}
	
	@Override
	public void enviarConfirmacaoHtmlEmail(Pedido pedido) {
		try {
			MimeMessage mimeMessage = this.getMessageMimeMessage(pedido);
			this.enviarHtmlEmail(mimeMessage);
		} catch (MessagingException e) {
			this.enviarConfirmacaoEmail(pedido);
		}
	}
	
	protected MimeMessage getMessageMimeMessage(Pedido pedido) throws MessagingException {
		MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setTo(pedido.getCliente().getEmail());
		helper.setFrom(this.sender);
		helper.setSubject("Pedido confirmado! Código: " + pedido.getId());
		helper.setSentDate(new Date(System.currentTimeMillis()));
		helper.setText(this.getHtmlEmail(pedido), true);
		return mimeMessage;
	}
	
	@Override
	public void enviarNovaSenhaNoEmail(Cliente cliente, String novaSenha) {
		SimpleMailMessage sm = this.getMessageEmailNovaSenha(cliente, novaSenha);
		enviarEmail(sm);
	}
	
	protected SimpleMailMessage getMessageEmailNovaSenha(Cliente cliente, String novaSenha) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(cliente.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + novaSenha);
		return sm;
	}

}
