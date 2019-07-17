package com.project.ecommerce.services;

import org.springframework.stereotype.Component;

@Component
public interface AuthService {
	
	void enviarNovaSenha(String email);
	
}
