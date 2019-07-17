package com.project.ecommerce.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="PEDIDO")
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="NUMERO")
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date instante;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy="pedido")
	private Pagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name="CLIENTE_ID")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="ENDERECO_DE_ENTREGA_ID")
	private Endereco enderecoDeEntrega;
	
	@OneToMany(mappedBy="id.pedido", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, orphanRemoval = true)
	private Set<ItemPedido> itens;
	
	/*****************************************************
	 *	CONSTRUTORES
	 ****************************************************/
	
	public Pedido() {
	}
	
	public Pedido(Long id, Date instante, Cliente cliente, Endereco enderecoDeEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
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

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}
	
	public Set<ItemPedido> getItens() {
		return Collections.unmodifiableSet(itens);
	}
	
	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	
	public void addItem(ItemPedido item) {
		if(this.itens==null){
			this.itens = new HashSet<ItemPedido>();
		}
		if(item!=null) {
			item.setPedido(this);
			this.itens.add(item);
		}
	}
	
	public Double getTotal() {
		Double soma = 0.0;
		for (ItemPedido itemPedido : itens) {
			soma = soma + itemPedido.getSubTotal();
		}
		return soma;
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
		Pedido other = (Pedido) obj;
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
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy hh:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append(" Número do pedido: ");
		builder.append(this.getId());
		builder.append(", Data do pedido: ");
		builder.append(sdf.format(this.getInstante()));
		builder.append(", Cliente: ");
		builder.append(this.getCliente().getNome());
		builder.append(", Situação do pagamento: ");
		builder.append(this.getPagamento().getEstadoPagamento().getDescricao());
		builder.append("\n Detalhes:\n");
		for (ItemPedido itemPedido : this.itens) {
			builder.append(itemPedido.toString());
		}
		builder.append(" Valor total: ");
		builder.append(nf.format(this.getTotal()));
		return builder.toString();
	}

}
