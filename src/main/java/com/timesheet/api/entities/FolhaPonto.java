package com.timesheet.api.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "folha_ponto")
public class FolhaPonto implements Serializable {
    private static final long serialVersionUID = 1L;

    //@EmbeddedId
    //private PontoUsuarioPK pontoUsuarioPK;

    
    @Column(name = "folha_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long folhaId;
    
    
    @Column(name = "usuario_id")
    //@Id    
    private Long usuarioId;
    
    
    @Column(name = "data_ponto")
    private LocalDate dataPonto;
    
    @MapsId("id")
    //@ManyToOne
    //@JoinColumn(name = "usuario_id")//TODO, referencedColumnName = "id")
    //@JoinColumn(name = "usuario_id", referencedColumnName = "id",insertable=false, updatable=false)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
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

    /*
    //    /PontoUsuarioPK pontoUsuarioPK, 
    public FolhaPonto(Usuario usuario, LocalTime horaEntrada, LocalTime horaInicioAlmoco, LocalTime horaFimAlmoco, LocalTime horaSaida) {
        //this.pontoUsuarioPK = pontoUsuarioPK;
        this.usuario = usuario;
        this.horaEntrada = horaEntrada;
        this.horaInicioAlmoco = horaInicioAlmoco;
        this.horaFimAlmoco = horaFimAlmoco;
        this.horaSaida = horaSaida;
    }
	*/
    
    
    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }
    
    
	public Long getFolhaId() {
		return folhaId;
	}

	public void setFolhaId(Long folhaId) {
		this.folhaId = folhaId;
	}
	
	

	public FolhaPonto(Long folhaId, Long usuarioId, LocalDate dataPonto, Usuario usuario, LocalTime horaEntrada,
			LocalTime horaInicioAlmoco, LocalTime horaFimAlmoco, LocalTime horaSaida) {
		super();
		this.folhaId = folhaId;
		//this.usuarioId = usuarioId;
		this.dataPonto = dataPonto;
		this.usuario = usuario;
		this.horaEntrada = horaEntrada;
		this.horaInicioAlmoco = horaInicioAlmoco;
		this.horaFimAlmoco = horaFimAlmoco;
		this.horaSaida = horaSaida;
	}

	/*
    public PontoUsuarioPK getPontoUsuarioPK() {
        return pontoUsuarioPK;
    }

    public void setPontoUsuarioPK(PontoUsuarioPK pontoUsuarioPK) {
        this.pontoUsuarioPK = pontoUsuarioPK;
    }

     */
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
	/*
	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
*/
	public LocalDate getDataPonto() {
		return dataPonto;
	}

	public void setDataPonto(LocalDate dataPonto) {
		this.dataPonto = dataPonto;
	}
	
	

   
}
