package com.timesheet.api.services;

import com.timesheet.api.dto.UsuarioDTO;
import com.timesheet.api.entities.Autorizacao;
import com.timesheet.api.entities.Usuario;
import com.timesheet.api.repositories.UsuarioRepository;

import com.timesheet.api.services.exception.DatabaseException;
import com.timesheet.api.services.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	private UsuarioRepository repo;
	private EmpresaService empresaService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserService(UsuarioRepository repo,EmpresaService empresaService, BCryptPasswordEncoder pe) {
		this.repo = repo;
		this.empresaService = empresaService;
		this.bCryptPasswordEncoder = pe;
	}
	
	
	public void criarUsuarioFuncionario(String nome, String email, String senha) {
		Long numeroAutorizacao = 2L;
		List<Autorizacao> autoriza = Arrays.asList(new Autorizacao(numeroAutorizacao, "ROLE_FUNCIONARIO"));
		Usuario usuarioASalvar = new Usuario();
		usuarioASalvar.setNome(nome);
		usuarioASalvar.setEmail(email);
		usuarioASalvar.setSenha(bCryptPasswordEncoder.encode(senha));
		usuarioASalvar.setAutorizacoes(autoriza);
		repo.save(usuarioASalvar);
	}
	
	public UsuarioDTO find(Long id) {

		//UserSS user = UserService.authenticated();
//		if (user == null && !id.equals(user.getId())) {
//			throw new AuthorizationException("Acesso negado");
//		}

		Usuario obj = ((Optional<Usuario>)repo.findById(id)).get();
		if (obj == null) {
			throw new ResourceNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName());
		}
		return new UsuarioDTO(obj);
	}

	public Usuario findUsuarioById(Long id) {
		return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario com o id: "+id +", não pode ser encontrado."));
	}

	public Usuario salvarUsuario(Usuario obj) {
		return repo.save(obj);
	}

	public void delete(Long id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Não é possível excluir porque há pedidos relacionados");
		}
	}

	public List<Usuario> findAll() {
		return repo.findAll();
	}

	public UsuarioDTO findByEmail(String email) {

		//UserSS user = UserService.authenticated();
		
		Usuario obj = repo.findOneByEmail(email);
		if (obj == null) {
			throw new ResourceNotFoundException(
					"Objeto não encontrado! Id: " + "Tipo: " + Usuario.class.getName());
		}
		return new UsuarioDTO(obj);
	}

	public static Usuario fromDTO(UsuarioDTO objDto) {
		//return new Usuario(objDto.getEmail(), objDto.getNome(), objDto.getClass(), objDto.getEmpresa(),null);
		return new Usuario();
	}
	
	/*
	public Usuario fromDTO(UsuarioNewDTO objDto) {
		Empresa empresa = empresaService.findById(objDto.getCodigoEmpresa());
		return new Usuario(objDto.getEmail(),objDto.getNome(), objDto.getTipo(), empresa,pe.encode(objDto.getSenha()));
	}
	
	private void updateData(Usuario newObj, Usuario obj) {
		newObj.setEmail(obj.getEmail());
	}
	*/
}
