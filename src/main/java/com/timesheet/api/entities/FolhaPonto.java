package com.timesheet.api.entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "folha_ponto")
public class FolhaPonto implements Serializable {
	
    private static final long serialVersionUID = 1L;

    //@EmbeddedId
    //private PontoUsuarioPK pontoUsuarioPK;

    
    
    @Column(name = "usuario_id")
    //@Id    
    private Long usuarioId;
    
    
    @Column(name = "ponto_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pontoId;
    
    
     
    @Column
    private LocalDate dataPonto;
    
    @MapsId("id")
    @ManyToOne
    //@JsonIgnore
    @JoinColumn(name = "usuario_id")//TODO, referencedColumnName = "id")
    //@JoinColumn(name = "usuario_id", referencedColumnName = "id",insertable=false, updatable=false)
    private Usuario usuario;

    @Column
    private LocalTime horaEntrada;

    @Column
    private LocalTime horaInicioAlmoco;

    @Column
    private LocalTime horaFimAlmoco;

    @Column
    private LocalTime horaSaida;

    public FolhaPonto(){
    }

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Long getPontoId() {
		return pontoId;
	}

	public void setPontoId(Long pontoId) {
		this.pontoId = pontoId;
	}

	public LocalDate getDataPonto() {
		return dataPonto;
	}

	public void setDataPonto(LocalDate dataPonto) {
		this.dataPonto = dataPonto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pontoId == null) ? 0 : pontoId.hashCode());
		result = prime * result + ((usuarioId == null) ? 0 : usuarioId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FolhaPonto other = (FolhaPonto) obj;
		if (pontoId == null) {
			if (other.pontoId != null)
				return false;
		} else if (!pontoId.equals(other.pontoId))
			return false;
		if (usuarioId == null) {
			if (other.usuarioId != null)
				return false;
		} else if (!usuarioId.equals(other.usuarioId))
			return false;
		return true;
	}

	public FolhaPonto(Long usuarioId, Long pontoId, LocalDate dataPonto, Usuario usuario, LocalTime horaEntrada,
			LocalTime horaInicioAlmoco, LocalTime horaFimAlmoco, LocalTime horaSaida) {
		super();
		this.usuarioId = usuarioId;
		this.pontoId = pontoId;
		this.dataPonto = dataPonto;
		this.usuario = usuario;
		this.horaEntrada = horaEntrada;
		this.horaInicioAlmoco = horaInicioAlmoco;
		this.horaFimAlmoco = horaFimAlmoco;
		this.horaSaida = horaSaida;
	}
    
    
}