package com.timesheet.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.timesheet.api.dto.BuscarPontoDTO;
import com.timesheet.api.dto.FolhaPontoDTO;
import com.timesheet.api.entities.FolhaPonto;
import com.timesheet.api.entities.PontoUsuarioPK;
import com.timesheet.api.entities.Usuario;
import com.timesheet.api.repositories.FolhaPontoRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PontoService {

	@Autowired
    private FolhaPontoRepository repository;
	
	@Autowired
    private UserService usuarioService;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");//("dd-MM-yyyy");

   

    public FolhaPonto baterPonto(FolhaPonto dto) {
        
        return this.repository.save(dto);
    }

    public List<FolhaPontoDTO> recuperarPontosByMonthAndUserId(Long userId, BuscarPontoDTO datas) {
        LocalDate inicio = formatDate(datas.getInicio());
        LocalDate fim = formatDate(datas.getFim());
        List<FolhaPonto> pontos = repository.findByUsuarioIdAndDataPontoBetween(userId,inicio, fim);
        return pontos.stream().map(ponto -> new FolhaPontoDTO(ponto)).collect(Collectors.toList());
    }
    
    public List<FolhaPonto> recuperarPontosByUserId(Long userId) {
       
        List<FolhaPonto> pontos = repository.pesquisar(userId);
        return pontos;
        //return pontos.stream().map(ponto -> new FolhaPontoDTO(ponto)).collect(Collectors.toList());
    }
    
    public FolhaPonto recuperarPontosByUserId2(Long userId) {
        
        FolhaPonto pontos = repository.findOneByUsuarioId(userId);
        return pontos;
    }

    public BigDecimal recuperarTotalHorasTrabalhadasByMonthAndUserId(Long userId, BuscarPontoDTO datas) {
        LocalDate inicio = formatDate(datas.getInicio());
        LocalDate fim = formatDate(datas.getFim());
        List<FolhaPonto> pontos = repository.findByUsuarioIdAndDataPontoBetween(userId,inicio, fim);
        BigDecimal sum = BigDecimal.ZERO;
        for (FolhaPonto ponto: pontos) {
            long periodoTrabalhado = ChronoUnit.HOURS.between(ponto.getHoraEntrada(), ponto.getHoraSaida());
            long periodoPausa = ChronoUnit.HOURS.between(ponto.getHoraInicioAlmoco(), ponto.getHoraFimAlmoco());
            sum = sum.add(BigDecimal.valueOf(periodoTrabalhado));
            sum = sum.subtract(BigDecimal.valueOf(periodoPausa));
        }
        return sum;
    }

    private FolhaPonto fromDTO(FolhaPontoDTO dto, Long funcionarioId, Long folhaId) {
        Usuario usuario = usuarioService.findUsuarioById(funcionarioId);
        LocalDate dataPonto = formatDate(dto.getDataPonto());
        return new FolhaPonto(
                //new PontoUsuarioPK(funcionarioId, dataPonto),
        		//dataPonto,
        		folhaId,
        		funcionarioId,
        		dataPonto,
                usuario,                
                dto.getHoraEntrada(),
                dto.getHoraInicioAlmoco(),
                dto.getHoraFimAlmoco(),
                dto.getHoraSaida() );
    }

    private LocalDate formatDate(String date) {
        return LocalDate.parse(date, dateFormatter);
    }
}
