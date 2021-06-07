package com.timesheet.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.timesheet.api.dto.EmpresaNewDTO;
import com.timesheet.api.entities.Empresa;
import com.timesheet.api.entities.Usuario;
import com.timesheet.api.services.UserService;

@RestController("/usuario")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') and #oauth2.hasScope('common-scope')")
    public ResponseEntity<Void> cadastrarUsuario(@RequestBody Usuario usuario) {
    
		userService.criarUsuarioFuncionario(usuario.getNome(), usuario.getEmail(), usuario.getSenha() );
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

}
