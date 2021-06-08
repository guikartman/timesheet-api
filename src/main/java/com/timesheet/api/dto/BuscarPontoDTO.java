package com.timesheet.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class BuscarPontoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "A busca deve conter uma data de inicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private String inicio;

    @NotNull(message = "O busca deve conter uma data final")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private String fim;

    public BuscarPontoDTO(String inicio, String fim){
        this.inicio = inicio;
        this.fim = fim;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }
}
