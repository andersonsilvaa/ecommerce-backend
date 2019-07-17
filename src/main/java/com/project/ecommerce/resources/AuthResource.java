package com.project.ecommerce.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.ecommerce.dto.EmailDto;
import com.project.ecommerce.security.JWTUtil;
import com.project.ecommerce.security.User;
import com.project.ecommerce.services.AuthService;
import com.project.ecommerce.services.impl.UserServiceImpl;

@RestController
@RequestMapping(value="/auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		User usuarioLogado = UserServiceImpl.getUsuarioLogado();
		String token = this.jwtUtil.gerarToken(usuarioLogado.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDto objDto) {
		this.authService.enviarNovaSenha(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}
	
}
