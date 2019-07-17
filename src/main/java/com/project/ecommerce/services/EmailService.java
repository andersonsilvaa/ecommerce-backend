package com.project.ecommerce.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.project.ecommerce.domain.Cliente;
import com.project.ecommerce.domain.Pedido;

public interface EmailService {

	void enviarConfirmacaoEmail(Pedido pedido);
	
	void enviarEmail(SimpleMailMessage message);
	
	void enviarConfirmacaoHtmlEmail(Pedido obj);
	
	void enviarHtmlEmail(MimeMessage msg);

	void enviarNovaSenhaNoEmail(Cliente cliente, String novaSenha);
}
