package com.timesheet.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.timesheet.api.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Transactional(readOnly=true)
	public Optional<Usuario> findByEmail(String email);
	
	@Transactional(readOnly=true)
	public Usuario findOneByEmail(String email);
	
	public List<Usuario> findByAutorizacoesDescricao(String autorizacoesDesricao);
	
	//@Transactional
	//public void save(String nome, String email, String senha, List<Autorizacao> auto);
	
	
}
