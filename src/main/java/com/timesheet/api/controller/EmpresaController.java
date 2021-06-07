package com.timesheet.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.timesheet.api.dto.EmpresaDTO;
import com.timesheet.api.dto.EmpresaNewDTO;
import com.timesheet.api.entities.Empresa;
import com.timesheet.api.entities.Usuario;
import com.timesheet.api.repositories.EmpresaRepository;
import com.timesheet.api.services.EmpresaService;

@RestController
@RequestMapping(value = "/empresa")
public class EmpresaController {
	
	@Autowired
    private EmpresaService service;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	/*
    @Autowired
    public EmpresaController(EmpresaService service) {
        this.service = service;
    }
    */

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') and #oauth2.hasScope('common-scope')")
    public ResponseEntity<Void> criarNovaEmpresa(@RequestBody EmpresaNewDTO novaEmpresa) {
        Empresa empresa = service.fromDTO(novaEmpresa);
        empresa = service.salvarEmpresa(empresa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(empresa.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}/usuarios")
    @PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') or hasAuthority('ROLE_ADMINISTRADOR') and #oauth2.hasScope('common-scope')")
    public ResponseEntity<Set<Usuario>> recuperarUsuariosByEmpresaId(@PathVariable Long id) {
        Empresa empresa = service.findById(id);
        Set<Usuario> usuarios = service.listarUsuariosByEmpresa(empresa);
        System.out.print(usuarios.toString());
        return ResponseEntity.ok(usuarios);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') or hasAuthority('ROLE_ADMINISTRADOR') and #oauth2.hasScope('common-scope')")
    public ResponseEntity<EmpresaDTO> recuperarEmpresaById(@PathVariable Long id) {
        Empresa empresa = service.findById(id);
        return ResponseEntity.ok(new EmpresaDTO(empresa));
    }
    
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') and #oauth2.hasScope('common-scope')")
    public ResponseEntity<List<Empresa>> recuperarTodasEmpresas(){//(Pageable pageable) {
    	return ResponseEntity.ok(service.findAll());        
    }
    
    /*
    @GetMapping("/page")
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') and #oauth2.hasScope('common-scope')")
    public ResponseEntity<Page<Empresa>> recuperarTodasEmpresasComPage(Pageable pageable) {
    	return ResponseEntity.ok(service.findAllWithPage(pageable));        
    }
    */
    
    @GetMapping("/page")
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') and #oauth2.hasScope('common-scope')")
	public Page<Empresa> search(@RequestParam(required = false, defaultValue = "") String razaoSocial, Pageable pageable) {
		return empresaRepository.findByRazaoSocialContaining(razaoSocial, pageable);
	}
   
}
