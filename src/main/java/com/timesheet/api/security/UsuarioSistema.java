package com.timesheet.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.timesheet.api.entities.Usuario;

public class UsuarioSistema extends User {
	
	private static final long serialVersionUID = 1L;
	
	private Usuario userAuthorization;
	
	public UsuarioSistema(Usuario userAuthorization, Collection<? extends GrantedAuthority> authorities) {		
		super(userAuthorization.getEmail(), userAuthorization.getSenha(), authorities);
		this.userAuthorization = userAuthorization;
	}

	public Usuario getUserAuthorization() {
		return userAuthorization;
	}
	

}
