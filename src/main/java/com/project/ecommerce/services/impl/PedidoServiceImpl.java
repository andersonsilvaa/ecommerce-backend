package com.project.ecommerce.services.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.ecommerce.domain.Cliente;
import com.project.ecommerce.domain.ItemPedido;
import com.project.ecommerce.domain.PagamentoComBoleto;
import com.project.ecommerce.domain.Pedido;
import com.project.ecommerce.domain.enums.EstadoPagamento;
import com.project.ecommerce.repositories.PedidoRepository;
import com.project.ecommerce.security.User;
import com.project.ecommerce.services.ClienteService;
import com.project.ecommerce.services.EmailService;
import com.project.ecommerce.services.PedidoService;
import com.project.ecommerce.services.ProdutoService;
import com.project.ecommerce.services.exceptions.AuthorizationException;
import com.project.ecommerce.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoServiceImpl implements PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BoletoServiceImpl boletoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	public Pedido consultaPorId(Long id) {
		Optional<Pedido> pedido = this.pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	@Transactional
	public Pedido salvar(Pedido pedido) {
		pedido.setInstante(new Date());
		pedido.setCliente(this.clienteService.consultaPorId(pedido.getCliente().getId()));
		pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
			this.boletoService.preencherPagamentoComBoleto(pagto, pedido.getInstante());
		}
		for (ItemPedido itemPedido : pedido.getItens()) {
			itemPedido.setDesconto(0.0);
			itemPedido.setProduto(this.produtoService.consultaPorId(itemPedido.getProduto().getId()));
			itemPedido.setPreco(itemPedido.getProduto().getPreco());
			itemPedido.setPedido(pedido);
		}
		pedido = this.pedidoRepository.save(pedido);
		this.emailService.enviarConfirmacaoHtmlEmail(pedido);
		return pedido;
	}
	
	public Page<Pedido> consultaPorPaginacao(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		User usuarioLogado = UserServiceImpl.getUsuarioLogado();
		if(usuarioLogado==null) throw new AuthorizationException("Acesso negado");
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Cliente cliente = this.clienteService.consultaPorId(usuarioLogado.getId());
		
		return this.pedidoRepository.findByCliente(cliente, pageRequest);
	}
	
}
