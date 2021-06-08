package com.timesheet.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.timesheet.api.entities.FolhaPonto;
import com.timesheet.api.entities.PontoUsuarioPK;

import java.time.LocalDate;
import java.util.List;

public interface FolhaPontoRepository extends JpaRepository<FolhaPonto, Long> {

    //List<FolhaPonto> findByPontoUsuarioPKUsuarioIdAndPontoUsuarioPKDataPontoBetween(final Long usuarioId,final LocalDate start, final LocalDate end);
	List<FolhaPonto> findByUsuarioIdAndDataPontoBetween(final Long usuarioId,final LocalDate start, final LocalDate end);
	
	//List<FolhaPonto> findAllByUsuarioId(Long usuarioId);
	FolhaPonto findOneByUsuarioId(Long usuarioId);
	
	@Query("SELECT folhaPonto FROM FolhaPonto folhaPonto WHERE :usuarioId = folhaPonto.usuarioId")
	List<FolhaPonto> pesquisar(Long usuarioId);

}
