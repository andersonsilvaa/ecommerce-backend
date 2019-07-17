package com.project.ecommerce.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.project.ecommerce.domain.Cliente;
import com.project.ecommerce.domain.enums.TipoCliente;
import com.project.ecommerce.dto.ClienteDto;
import com.project.ecommerce.repositories.ClienteRepository;
import com.project.ecommerce.resources.exception.FieldMessage;
import com.project.ecommerce.services.validation.utils.ValidatorUtil;

public class ClienteConstraintValidator implements ConstraintValidator<ClienteValidator, ClienteDto> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteValidator ann) {
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean isValid(ClienteDto objDto, ConstraintValidatorContext context) {
		List<FieldMessage> lst = new ArrayList<>();
		
		Cliente clienteExistente = this.clienteRepository.findByEmail(objDto.getEmail());
		Map<String, String> map = (Map<String, String>) this.request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = (map.get("id")==null) ? null : new Long(map.get("id"));
		
		/*****************************************************
		 *	VALIDAÇÕES 
		 ****************************************************/
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) &&
				!ValidatorUtil.isValidCPF(objDto.getCpfOuCnpj())) {
			lst.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) &&
				!ValidatorUtil.isValidCNPJ(objDto.getCpfOuCnpj())) {
			lst.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		if(clienteExistente!=null && uriId == null) {
			lst.add(new FieldMessage("Email", "Email já existente")); 
		}
		if(clienteExistente!=null && !clienteExistente.getId().equals(uriId)) {
			lst.add(new FieldMessage("Email", "Email já existente"));
		}
		for (FieldMessage e : lst) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return lst.isEmpty();
	}
}