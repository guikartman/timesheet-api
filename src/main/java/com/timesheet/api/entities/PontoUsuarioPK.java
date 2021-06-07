package com.timesheet.api.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class PontoUsuarioPK implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long usuarioId;
    private LocalDate dataPonto;

    public PontoUsuarioPK(){}

    public PontoUsuarioPK(Long usuarioId, LocalDate dataPonto) {
    //public PontoUsuarioPK(LocalDate dataPonto) {
        this.usuarioId = usuarioId;
        this.dataPonto = dataPonto;
    }

    
    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
    

    public LocalDate getDataPonto() {
        return dataPonto;
    }

    public void setDataPonto(LocalDate dataPonto) {
        this.dataPonto = dataPonto;
    }
}
