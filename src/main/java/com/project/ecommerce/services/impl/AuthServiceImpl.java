package com.project.ecommerce.services.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.ecommerce.domain.Cliente;
import com.project.ecommerce.repositories.ClienteRepository;
import com.project.ecommerce.services.AuthService;
import com.project.ecommerce.services.EmailService;
import com.project.ecommerce.services.exceptions.ObjectNotFoundException;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EmailService emailService;
	
	public void enviarNovaSenha(String email) {
		
		Cliente cliente = clienteRepository.findByEmail(email);
		if (cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String novaSenha = this.geraNovaSenha();
		cliente.setSenha(new BCryptPasswordEncoder().encode(novaSenha));
		
		this.clienteRepository.save(cliente);
		this.emailService.enviarNovaSenhaNoEmail(cliente, novaSenha);
	}

	private String geraNovaSenha() {
		char[] vet = new char[10];
		for (int i=0; i<10; i++) {
			vet[i] = this.caracterAleatorio();
		}
		return new String(vet);
	}

	private char caracterAleatorio() {
		Random rand = new Random();
		int opt = rand.nextInt(3);
		if (opt == 0) { // GERA UM DIGITO
			return (char) (rand.nextInt(10) + 48);
		}
		else if (opt == 1) { // GERA LETRA MAIUSCULA
			return (char) (rand.nextInt(26) + 65);
		}
		else { // GERA LETRA MINUSCULA
			return (char) (rand.nextInt(26) + 97);
		}
	}
}