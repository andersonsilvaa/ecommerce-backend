package com.project.ecommerce.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORIA")
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="NOME")
	private String nome;
	
	@ManyToMany(mappedBy="categorias", cascade = { CascadeType.PERSIST, CascadeType.MERGE})
	private List<Produto> produtos;
	
	/*****************************************************
	 *	CONSTRUTORES
	 ****************************************************/
	
	public Categoria() {
	}
	
	public Categoria(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	/*****************************************************
	 *	METODOS ACESSORES
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
	
	public List<Produto> getProdutos() {
		if(this.produtos==null) {
			this.produtos = new ArrayList<Produto>();
		}
		return Collections.unmodifiableList(this.produtos);
	}
	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public void addProduto(Produto produto) {
		if(this.produtos==null) {
			this.produtos = new ArrayList<Produto>();
		}
		if(produto!=null) {
			produto.addCategoria(this);
			this.produtos.add(produto);
		}
	}
	
	/*****************************************************
	 *	MÉTODO HASCODE E EQUALS
	 ****************************************************/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/*****************************************************
	 *	SOBRESCRITA NO MÉTODO toString
	 ****************************************************/
	
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + ", produtos=" + produtos + "]";
	}
	
}
