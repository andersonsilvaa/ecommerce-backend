package com.project.ecommerce.services.impl;

import org.springframework.security.core.context.SecurityContextHolder;

import com.project.ecommerce.security.User;

public class UserServiceImpl {

	public static User getUsuarioLogado() {
		User usuarioLogado = null;
		try {
			usuarioLogado = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
		}
		return usuarioLogado;
	}
}
