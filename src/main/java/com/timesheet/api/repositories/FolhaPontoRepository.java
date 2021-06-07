package com.timesheet.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.timesheet.api.entities.FolhaPonto;
import com.timesheet.api.entities.PontoUsuarioPK;

import java.time.LocalDate;
import java.util.List;

public interface FolhaPontoRepository extends JpaRepository<FolhaPonto, Long> {

    //List<FolhaPonto> findByPontoUsuarioPKUsuarioIdAndPontoUsuarioPKDataPontoBetween(final Long usuarioId,final LocalDate start, final LocalDate end);
	List<FolhaPonto> findByUsuarioIdAndDataPontoBetween(final Long usuarioId,final LocalDate start, final LocalDate end);
}
