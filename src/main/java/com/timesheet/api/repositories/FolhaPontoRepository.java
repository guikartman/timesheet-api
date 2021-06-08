package com.timesheet.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	@Query("SELECT new map(dataPonto AS dataPonto, usuarioId AS usuarioId, horaEntrada AS horaEntrada, horaInicioAlmoco AS horaInicioAlmoco, horaFimAlmoco AS  horaFimAlmoco, horaSaida AS horaSaida) "
	+ " FROM FolhaPonto folhaPonto WHERE folhaPonto.usuarioId = :usuarioId GROUP BY folhaPonto.dataPonto, folhaPonto.usuarioId, folhaPonto.horaEntrada, folhaPonto.horaInicioAlmoco, "
	+ " folhaPonto.horaFimAlmoco, folhaPonto.horaSaida")
	Page<FolhaPonto> pesquisar(Long usuarioId, Pageable pageable);
	/*@Query(
	value = " SELECT data_ponto FROM folha_ponto "
			//+" WHERE id = :usuarioId  " 
			+ " GROUP BY data_ponto;",
	  countQuery = "SELECT count(data_ponto) FROM folha_ponto", 
	  nativeQuery = true)*/
	

}
//usuario_id, data_ponto, hora_entrada, hora_fim_almoco, hora_inicio_almoco, hora_saida
