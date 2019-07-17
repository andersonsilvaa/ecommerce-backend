package com.project.ecommerce.services.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ecommerce.domain.Categoria;
import com.project.ecommerce.domain.Cidade;
import com.project.ecommerce.domain.Cliente;
import com.project.ecommerce.domain.Endereco;
import com.project.ecommerce.domain.Estado;
import com.project.ecommerce.domain.ItemPedido;
import com.project.ecommerce.domain.Pagamento;
import com.project.ecommerce.domain.PagamentoComBoleto;
import com.project.ecommerce.domain.PagamentoComCartao;
import com.project.ecommerce.domain.Pedido;
import com.project.ecommerce.domain.Produto;
import com.project.ecommerce.domain.Telefone;
import com.project.ecommerce.domain.enums.EstadoPagamento;
import com.project.ecommerce.domain.enums.Perfil;
import com.project.ecommerce.domain.enums.TipoCliente;
import com.project.ecommerce.repositories.CategoriaRepository;
import com.project.ecommerce.repositories.ClienteRepository;
import com.project.ecommerce.repositories.EstadoRepository;
import com.project.ecommerce.repositories.ItemPedidoRepository;
import com.project.ecommerce.repositories.PedidoRepository;

@Service
public class DBServiceImpl {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public void dataBaseTest() throws ParseException {
		Categoria categoriaInformatica = new Categoria(null, "Informática");
		Categoria categoriaEscritorio = new Categoria(null, "Escritório");
		Categoria categoriaCasaMesaEBanho = new Categoria(null, "Casa, mesa e banho");
		Categoria categoriaEletronico = new Categoria(null, "Eletrônicos");
		Categoria categoriaJardinagem = new Categoria(null, "Jardinagem");
		Categoria categoriaDecoracao = new Categoria(null, "Decoração");
		Categoria categoriaPerfumaria = new Categoria(null, "Perfumaria");
		
		Produto produtoComputador = new Produto(null, "Computador", 2000.00);
		Produto produtoImpressora = new Produto(null, "Impressora", 800.00);
		Produto produtoMouse = new Produto(null, "Mouse", 80.00); 
		Produto produtoMesaEscritorio = new Produto(null, "Mesa de escritório", 300.00); 
		Produto produtoToalha = new Produto(null, "Toalha", 50.00); 
		Produto produtoColcha = new Produto(null, "Colcha", 200.00); 
		Produto produtoTV = new Produto(null, "TV true color", 1200.00); 
		Produto produtoRocadeira = new Produto(null, "Roçadeira", 800.00); 
		Produto produtoAbajour = new Produto(null, "Abajour", 100.00); 
		Produto produtoPendente = new Produto(null, "Pendente", 180.00); 
		Produto produtoShampoo = new Produto(null, "Shampoo", 90.00); 
		
		Produto p12 = new Produto(null, "Produto 12", 10.00);
		Produto p13 = new Produto(null, "Produto 13", 10.00);
		Produto p14 = new Produto(null, "Produto 14", 10.00);
		Produto p15 = new Produto(null, "Produto 15", 10.00);
		Produto p16 = new Produto(null, "Produto 16", 10.00);
		Produto p17 = new Produto(null, "Produto 17", 10.00);
		Produto p18 = new Produto(null, "Produto 18", 10.00);
		Produto p19 = new Produto(null, "Produto 19", 10.00);
		Produto p20 = new Produto(null, "Produto 20", 10.00);
		Produto p21 = new Produto(null, "Produto 21", 10.00);
		Produto p22 = new Produto(null, "Produto 22", 10.00);
		Produto p23 = new Produto(null, "Produto 23", 10.00);
		Produto p24 = new Produto(null, "Produto 24", 10.00);
		Produto p25 = new Produto(null, "Produto 25", 10.00);
		Produto p26 = new Produto(null, "Produto 26", 10.00);
		Produto p27 = new Produto(null, "Produto 27", 10.00);
		Produto p28 = new Produto(null, "Produto 28", 10.00);
		Produto p29 = new Produto(null, "Produto 29", 10.00);
		Produto p30 = new Produto(null, "Produto 30", 10.00);
		Produto p31 = new Produto(null, "Produto 31", 10.00);
		Produto p32 = new Produto(null, "Produto 32", 10.00);
		Produto p33 = new Produto(null, "Produto 33", 10.00);
		Produto p34 = new Produto(null, "Produto 34", 10.00);
		Produto p35 = new Produto(null, "Produto 35", 10.00);
		Produto p36 = new Produto(null, "Produto 36", 10.00);
		Produto p37 = new Produto(null, "Produto 37", 10.00);
		Produto p38 = new Produto(null, "Produto 38", 10.00);
		Produto p39 = new Produto(null, "Produto 39", 10.00);
		Produto p40 = new Produto(null, "Produto 40", 10.00);
		Produto p41 = new Produto(null, "Produto 41", 10.00);
		Produto p42 = new Produto(null, "Produto 42", 10.00);
		Produto p43 = new Produto(null, "Produto 43", 10.00);
		Produto p44 = new Produto(null, "Produto 44", 10.00);
		Produto p45 = new Produto(null, "Produto 45", 10.00);
		Produto p46 = new Produto(null, "Produto 46", 10.00);
		Produto p47 = new Produto(null, "Produto 47", 10.00);
		Produto p48 = new Produto(null, "Produto 48", 10.00);
		Produto p49 = new Produto(null, "Produto 49", 10.00);
		Produto p50 = new Produto(null, "Produto 50", 10.00);
		
		List<Produto> lstPrimeiroProduto = Arrays.asList(produtoComputador, produtoImpressora, produtoMouse,
														 p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, 
														 p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, 
														 p38, p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50);
		lstPrimeiroProduto.forEach(produto->categoriaInformatica.addProduto(produto));
		
		List<Produto> lstSegundoProduto = Arrays.asList(produtoImpressora, produtoMesaEscritorio);
		lstSegundoProduto.forEach(produto->categoriaEscritorio.addProduto(produto));
		
		List<Produto> lstTerceiroProduto = Arrays.asList(produtoToalha, produtoColcha);
		lstTerceiroProduto.forEach(produto->categoriaCasaMesaEBanho.addProduto(produto));
		
		List<Produto> lstQuartoProduto = Arrays.asList(produtoComputador, produtoImpressora, produtoMouse, produtoTV);
		lstQuartoProduto.forEach(produto->categoriaEletronico.addProduto(produto));
		
		categoriaJardinagem.addProduto(produtoRocadeira);
		
		List<Produto> lstQuintoProduto = Arrays.asList(produtoAbajour, produtoPendente);
		lstQuintoProduto.forEach(produto->categoriaDecoracao.addProduto(produto));
		
		categoriaPerfumaria.addProduto(produtoShampoo);
		
		this.categoriaRepository.saveAll(Arrays.asList(categoriaInformatica, categoriaEscritorio, categoriaCasaMesaEBanho, categoriaEletronico,
				categoriaJardinagem, categoriaDecoracao, categoriaPerfumaria));
		
		/***************************************************************************************************/
		
		Estado estadoMinasGerais = new Estado(null, "Minas Gerais");
		Estado estadoSaoPaulo = new Estado(null, "São Paulo");
		
		Cidade cidadeUberlandia = new Cidade(null, "Uberlândia", estadoMinasGerais);
		Cidade cidadeSaoPaulo = new Cidade(null, "São Paulo", estadoSaoPaulo);
		Cidade cidadeCampinas = new Cidade(null, "Campinas", estadoSaoPaulo);
		
		List<Cidade> cidades = Arrays.asList(cidadeSaoPaulo, cidadeCampinas);
		cidades.forEach(cidade->estadoSaoPaulo.addCidade(cidade));
		estadoMinasGerais.addCidade(cidadeUberlandia);
		
		this.estadoRepository.saveAll(Arrays.asList(estadoMinasGerais, estadoSaoPaulo));
		
		/***************************************************************************************************/
		
		Cliente clienteMaria = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA, "123");
		
		Cliente clienteAnderson = new Cliente(null, "Anderson Silva", "andersonsilva0692@gmail.com", "09187127008", TipoCliente.PESSOAFISICA, "123");
		clienteAnderson.addPerfil(Perfil.ADMIM);
		
		Telefone primeiroTelefone = new Telefone(null, "27363323", clienteMaria);
		Telefone segundoTelefone = new Telefone(null, "93838393", clienteAnderson);
		
		clienteMaria.addTelefone(primeiroTelefone);
		clienteAnderson.addTelefone(segundoTelefone);
		
		Endereco enderecoRuaFlores = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", clienteMaria, cidadeUberlandia);
		Endereco enderecoRosaDeCarmo = new Endereco(null, "Rua Rosa de Carmo", "800", "Apto 899", "Jardim Querência", "98781241", clienteMaria, cidadeUberlandia);
		Endereco enderecoAvenidaMatos = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", clienteAnderson, cidadeSaoPaulo);
		
		clienteMaria.addEndereco(enderecoRuaFlores);
		clienteMaria.addEndereco(enderecoRosaDeCarmo);
		clienteAnderson.addEndereco(enderecoAvenidaMatos);
		
		List<Cliente> clientes = Arrays.asList(clienteMaria, clienteAnderson);
		
		this.clienteRepository.saveAll(clientes);
		
		/***************************************************************************************************/
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido primeiroPedido = new Pedido(null, sdf.parse("30/09/2017 10:32"), clienteMaria, enderecoRuaFlores);
		Pedido segundoPedido = new Pedido(null, sdf.parse("10/10/2017 19:35"), clienteAnderson, enderecoAvenidaMatos);
		
		Pagamento pagamentoComCartao = new PagamentoComCartao(null, EstadoPagamento.QUITADO, primeiroPedido, 6);
		primeiroPedido.setPagamento(pagamentoComCartao);
		
		Pagamento pagamentoComBoleto = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, segundoPedido, sdf.parse("20/10/2017 00:00"), null);
		segundoPedido.setPagamento(pagamentoComBoleto);
		
		clienteMaria.addPedido(primeiroPedido);
		clienteAnderson.addPedido(segundoPedido);
		
		this.pedidoRepository.saveAll(Arrays.asList(primeiroPedido, segundoPedido));
		
		/***************************************************************************************************/
		
		ItemPedido primeiroItemPedido = new ItemPedido(primeiroPedido, produtoComputador, 0.00, 1, 2000.00);
		ItemPedido segundoItemPedido = new ItemPedido(primeiroPedido, produtoMouse, 0.00, 2, 80.00);
		ItemPedido terceiroItemPedido = new ItemPedido(segundoPedido, produtoImpressora, 100.00, 1, 800.00);
		
		List<ItemPedido> itens = Arrays.asList(primeiroItemPedido, segundoItemPedido);
		itens.forEach(item->primeiroPedido.addItem(item));
		segundoPedido.addItem(terceiroItemPedido);
		
		produtoComputador.addItem(primeiroItemPedido);
		produtoImpressora.addItem(terceiroItemPedido);
		produtoMouse.addItem(segundoItemPedido);
		
		this.itemPedidoRepository.saveAll(Arrays.asList(primeiroItemPedido, segundoItemPedido, terceiroItemPedido));
	}
	
}
