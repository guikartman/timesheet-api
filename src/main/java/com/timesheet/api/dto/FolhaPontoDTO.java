package com.timesheet.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.timesheet.api.entities.FolhaPonto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;

public class FolhaPontoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm")
    private LocalTime horaEntrada;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm")
    private LocalTime horaInicioAlmoco;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm")
    private LocalTime horaFimAlmoco;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm")
    private LocalTime horaSaida;

    @NotNull(message = "O ponto deve ter 1 data")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private String dataPonto;

    public FolhaPontoDTO(LocalTime horaEntrada, LocalTime horaInicioAlmoco, LocalTime horaFimAlmoco, LocalTime horaSaida, String dataPonto) {
        this.horaEntrada = horaEntrada;
        this.horaInicioAlmoco = horaInicioAlmoco;
        this.horaFimAlmoco = horaFimAlmoco;
        this.horaSaida = horaSaida;
        this.dataPonto = dataPonto;
    }

    public FolhaPontoDTO(FolhaPonto folhaPonto) {
        this.horaEntrada = folhaPonto.getHoraEntrada();
        this.horaInicioAlmoco = folhaPonto.getHoraInicioAlmoco();
        this.horaFimAlmoco = folhaPonto.getHoraFimAlmoco();
        this.horaSaida = folhaPonto.getHoraSaida();
        //this.dataPonto = folhaPonto.getPontoUsuarioPK().getDataPonto().toString();
    }

	public LocalTime getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(LocalTime horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public LocalTime getHoraInicioAlmoco() {
		return horaInicioAlmoco;
	}

	public void setHoraInicioAlmoco(LocalTime horaInicioAlmoco) {
		this.horaInicioAlmoco = horaInicioAlmoco;
	}

	public LocalTime getHoraFimAlmoco() {
		return horaFimAlmoco;
	}

	public void setHoraFimAlmoco(LocalTime horaFimAlmoco) {
		this.horaFimAlmoco = horaFimAlmoco;
	}

	public LocalTime getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(LocalTime horaSaida) {
		this.horaSaida = horaSaida;
	}

	public String getDataPonto() {
		return dataPonto;
	}

	public void setDataPonto(String dataPonto) {
		this.dataPonto = dataPonto;
	}

    

}
