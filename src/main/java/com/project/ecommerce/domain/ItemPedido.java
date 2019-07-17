package com.project.ecommerce.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ITEMPEDIDO")
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	@JsonIgnore
	private ItemPedidoPK id;
	
	@Column(name="desconto")
	private Double desconto;
	
	@Column(name="quantidade")
	private Integer quantidade;
	
	@Column(name="PRECO")
	private Double preco;
	
	/*****************************************************
	 *	CONSTRUTORES
	 ****************************************************/
	
	public ItemPedido() {
	}

	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		this.setPedido(pedido);
		this.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	/*****************************************************
	 *	METODOS ACESSORES
	 ****************************************************/

	public void setPedido(Pedido pedido) {
		if(this.id==null) {
			this.id = new ItemPedidoPK();
		}
		this.id.setPedido(pedido);
	}
	
	@JsonIgnore
	public Pedido getPedido() {
		return this.id.getPedido();
	}
	
	public void setProduto(Produto produto) {
		if(this.id==null) {
			this.id = new ItemPedidoPK();
		}
		this.id.setProduto(produto);
	}
	
	public Produto getProduto() {
		return this.id.getProduto();
	}
	
	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public Double getSubTotal() {
		return (preco - desconto) * quantidade;
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
		ItemPedido other = (ItemPedido) obj;
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
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append(" " +this.getProduto().getNome());
		builder.append(", Qte: ");
		builder.append(this.getQuantidade());
		builder.append(", Preço unitário: ");
		builder.append(nf.format(this.getPreco()));
		builder.append(", Subtotal: ");
		builder.append(nf.format(this.getSubTotal()));
		builder.append("\n");
		return builder.toString();
	}
	
}
