package com.timesheet.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.timesheet.api.dto.EmpresaNewDTO;
import com.timesheet.api.dto.UsuarioDTO;
import com.timesheet.api.entities.Empresa;
import com.timesheet.api.entities.Usuario;
import com.timesheet.api.repositories.EmpresaRepository;
import com.timesheet.api.services.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

	@Autowired
    private EmpresaRepository repository;

	/*
    @Autowired
    public EmpresaService(EmpresaRepository repository) {
        this.repository = repository;
    }
	*/
    public Empresa findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Empresa com o id informado não pode ser encontrada"));
    }
    
    public List<Empresa> findAll() {
        return repository.findAll();
    }
    
    public Page<Empresa> findAllWithPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Empresa salvarEmpresa(Empresa empresa) {
        return repository.save(empresa);
    }

    public Set<Usuario> listarUsuariosByEmpresa(Empresa empresa) {
        return empresa.getUsuarios();
    }
    /*
    public Page<UsuarioDTO> listarUsuariosByEmpresa(Empresa empresa) {
        List<UsuarioDTO> lista = empresa.getUsuarios().stream().map(usuario -> {
            return new UsuarioDTO(usuario);
        }).collect(Collectors.toList());
        
        return new PageImpl<>(lista);
    }
    */
    /*
    public Page<Usuario> listarUsuariosByEmpresa2(Empresa empresa) {
        List<Usuario> lista = empresa.getUsuarios().stream().map(usuario -> {
            return new Usuario(usuario);
        }).collect(Collectors.toList());
        
        return new PageImpl<>(lista);
    }
	
    /*
     * public List<UsuarioDTO> listarUsuariosByEmpresa(Empresa empresa) {
        return empresa.getUsuarios().stream().map(usuario -> {
            return new UsuarioDTO(usuario);
        }).collect(Collectors.toList());
    }*/
    public Empresa fromDTO(EmpresaNewDTO objDto) {
        return new Empresa(objDto.getCnpj(),objDto.getRazaoSocial());
    }
}
