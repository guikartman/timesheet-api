package com.timesheet.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.timesheet.api.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa,Long> {
}
