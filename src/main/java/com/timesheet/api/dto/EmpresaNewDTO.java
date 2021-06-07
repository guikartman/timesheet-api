package com.timesheet.api.dto;

import org.hibernate.validator.constraints.br.CNPJ;

import com.timesheet.api.entities.Empresa;
import com.timesheet.api.entities.Usuario;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class EmpresaNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "O campo CNPJ é obrigatorio")
    @CNPJ(message = "O CNPJ deve ser valido")
    private String cnpj;

    @NotNull(message = "A empresa deve ter uma Razão Social")
    private String razaoSocial;
    
    
    private Set<Usuario> usuarios = new HashSet<>();

    public EmpresaNewDTO(Empresa empresa) {
        this.cnpj = empresa.getCnpj();
        this.razaoSocial = empresa.getRazaoSocial();
        this.usuarios = empresa.getUsuarios();
    }

    public EmpresaNewDTO(){
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
    
    
}
