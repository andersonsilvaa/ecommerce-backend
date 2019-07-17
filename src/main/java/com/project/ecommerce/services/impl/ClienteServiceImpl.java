package com.project.ecommerce.services.impl;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.project.ecommerce.domain.Cliente;
import com.project.ecommerce.domain.enums.Perfil;
import com.project.ecommerce.dto.ClienteDto;
import com.project.ecommerce.repositories.ClienteRepository;
import com.project.ecommerce.security.User;
import com.project.ecommerce.services.ClienteService;
import com.project.ecommerce.services.S3Service;
import com.project.ecommerce.services.exceptions.AuthorizationException;
import com.project.ecommerce.services.exceptions.DataIntegrityException;
import com.project.ecommerce.services.exceptions.ObjectNotFoundException;
import com.project.ecommerce.services.impl.utils.ImageUtil;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private S3Service s3Service;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;
	
	public Cliente consultaPorId(Long id) {
		
		User usuarioLogado = UserServiceImpl.getUsuarioLogado();
		if(usuarioLogado==null || !usuarioLogado.hasRole(Perfil.ADMIM) && !id.equals(usuarioLogado.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Cliente> cliente = this.clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente consultaPorEmail(String email) {
		
		User usuarioLogado = UserServiceImpl.getUsuarioLogado();
		if(usuarioLogado==null || !usuarioLogado.hasRole(Perfil.ADMIM) && !email.equals(usuarioLogado.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Cliente cliente = this.clienteRepository.findByEmail(email);
		
		if(cliente==null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + usuarioLogado.getId() + ", Tipo: " + Cliente.class.getName());
		}
		
		return cliente;
	}
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		if(cliente.getId()!=null) {
			Cliente obj = this.consultaPorId(cliente.getId());
			this.atualizar(obj, cliente);
		}
		return this.clienteRepository.save(cliente);
	}

	private void atualizar(Cliente obj, Cliente cliente) {
		obj.setNome(cliente.getNome());
		obj.setEmail(cliente.getEmail());
	}

	public void excluir(Long id) {
		this.consultaPorId(id);
		try {
			this.clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há pedidos relacionados");
		}
	}

	public List<ClienteDto> consultaTodos() {
		List<Cliente> lst = this.clienteRepository.findAll();
		List<ClienteDto> lstDto = lst.stream().map(obj -> new ClienteDto(obj)).collect(Collectors.toList());
		return lstDto;
	}
	
	public Page<ClienteDto> consultaPorPaginacao(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Cliente> lst = this.clienteRepository.findAll(pageRequest);
		Page<ClienteDto> lstDto = lst.map(obj -> new ClienteDto(obj));
		return lstDto;
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		
		User usuarioLogado = UserServiceImpl.getUsuarioLogado();
		if(usuarioLogado==null) {
			throw new AuthorizationException("Acesso negado");
		}
		
		BufferedImage jpgImage = ImageUtil.getJpgImageFromFile(multipartFile);
		jpgImage = ImageUtil.cropSquare(jpgImage);
		jpgImage = ImageUtil.resize(jpgImage, size);
		
		String fileName = prefix + usuarioLogado.getId() + ".jpg";
		
		return this.s3Service.uploadFile(ImageUtil.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
	
}
