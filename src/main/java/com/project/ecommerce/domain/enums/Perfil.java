package com.project.ecommerce.domain.enums;

public enum Perfil {
	
	ADMIM(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private Integer codigo;
	private String descricao;
	
	private Perfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer codigo) {
		
		if(codigo==null) {
			return null;
		}
		
		for (Perfil perfil : Perfil.values()) {
			if(perfil.getCodigo().equals(codigo)) {
				return perfil;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: "+codigo);
	}

}
