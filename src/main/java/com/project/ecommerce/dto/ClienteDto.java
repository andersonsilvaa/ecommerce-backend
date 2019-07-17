package com.project.ecommerce.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.project.ecommerce.domain.Cidade;
import com.project.ecommerce.domain.Cliente;
import com.project.ecommerce.domain.Endereco;
import com.project.ecommerce.domain.Telefone;
import com.project.ecommerce.domain.enums.TipoCliente;
import com.project.ecommerce.services.validation.ClienteValidator;

@ClienteValidator
public class ClienteDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty
	@Email(message="E-mail inválido")
	private String email;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String cpfOuCnpj;
	
	private Integer tipo;
	
	@NotEmpty(message="Preenchimento obrigatório")
	private String senha;
	
	private EnderecoDto endereco;
	
	private CidadeDto cidade;
	
	private List<TelefoneDto> telefones;
	
	/*****************************************************
	 *	CONSTRUTORES
	 ****************************************************/
	
	public ClienteDto() {
	}
	
	public ClienteDto(Cliente cliente) {
		if(cliente!=null) {
			this.id = cliente.getId();
			this.nome = cliente.getNome();
			this.email = cliente.getEmail();
			this.cpfOuCnpj = cliente.getCpfOuCnpj();
			this.tipo = (cliente.getTipo()==null) ? null : cliente.getTipo().getCodigo();
		}
	}
	
	/*****************************************************
	 *	CONSTRUTORES
	 ****************************************************/
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public EnderecoDto getEndereco() {
		return endereco;
	}
	
	public void setEndereco(EnderecoDto endereco) {
		this.endereco = endereco;
	}

	public List<TelefoneDto> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<TelefoneDto> telefones) {
		this.telefones = telefones;
	}

	public CidadeDto getCidade() {
		return cidade;
	}
	
	public void setCidade(CidadeDto cidade) {
		this.cidade = cidade;
	}
	
	public Cliente getDtoToEntity(Long... id) {
		if(id!=null) {
			for (Long idCliente : id) {
				this.id = idCliente;
				break;
			}
		}
		Cliente cliente = new Cliente(this.id, this.nome, this.email, this.cpfOuCnpj, TipoCliente.toEnum(this.tipo), this.senha);
		Endereco endereco = new Endereco(this.endereco, new Cidade(this.cidade));
		cliente.addEndereco(endereco);
		for (TelefoneDto telefone : telefones) {
			if(!telefone.getNumero().isEmpty()) {
				cliente.addTelefone(new Telefone(telefone));
			}
		}
		return cliente;
	}
	
	/*****************************************************
	 *	SOBRESCRITA NO MÉTODO toString
	 ****************************************************/
	
	@Override
	public String toString() {
		return "ClienteDto [id=" + id + ", nome=" + nome + ", email=" + email + ", cpfOuCnpj=" + cpfOuCnpj + ", tipo="
				+ tipo + ", endereco=" + endereco + ", telefones=" + telefones + ", cidade=" + cidade + "]";
	}
	
}
