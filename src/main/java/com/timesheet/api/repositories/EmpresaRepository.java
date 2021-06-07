package com.timesheet.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.timesheet.api.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa,Long> {
	
	public Page<Empresa> findByRazaoSocialContaining(String razaoSocial, Pageable pageable);
}
