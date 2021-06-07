package com.timesheet.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.timesheet.api.dto.BuscarPontoDTO;
import com.timesheet.api.dto.FolhaPontoDTO;
import com.timesheet.api.entities.FolhaPonto;
import com.timesheet.api.services.PontoService;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
public class PontoController {

	@Autowired
    private PontoService service;

	
	/*
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') or hasAuthority('ROLE_ADMINISTRADOR') and #oauth2.hasScope('common-scope')")
	@PostMapping("/{user_id}/pontos")
    public ResponseEntity<Void> baterPonto(@PathVariable(value = "user_id") Long id, @RequestBody FolhaPontoDTO folhaPonto) {
        FolhaPonto ponto = service.baterPonto(folhaPonto, id);
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                //.path("/{data}").buildAndExpand(ponto.getUsuario().getId()).toUri();//.buildAndExpand(ponto.getPontoUsuarioPK().getDataPonto()).toUri(); TODO
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{data}").buildAndExpand(ponto.getDataPonto()).toUri();
        return ResponseEntity.created(uri).build();
    }
	*/
	
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') or hasAuthority('ROLE_ADMINISTRADOR') and #oauth2.hasScope('common-scope')")
	@PostMapping("/{user_id}/pontos")
    public ResponseEntity<FolhaPonto> baterPonto(@PathVariable(value = "user_id") Long id, @RequestBody FolhaPontoDTO folhaPonto) {
        FolhaPonto ponto = service.baterPonto(folhaPonto, id);
        
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                //.path("/{data}").buildAndExpand(ponto.getUsuario().getId()).toUri();//.buildAndExpand(ponto.getPontoUsuarioPK().getDataPonto()).toUri(); TODO
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                //.path("/{data}").buildAndExpand(ponto.getDataPonto()).toUri();
        System.out.println(ponto.toString());
         return ResponseEntity.status(HttpStatus.CREATED).body(ponto);
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') or hasAuthority('ROLE_ADMINISTRADOR') and #oauth2.hasScope('common-scope')")
    @GetMapping("/{user_id}/pontos")
    public ResponseEntity<List<FolhaPontoDTO>> listarPontosByDate(@PathVariable(value = "user_id") Long id, @RequestBody BuscarPontoDTO datas) {
        List<FolhaPontoDTO> pontos = service.recuperarPontosByMonthAndUserId(id, datas);
        return ResponseEntity.ok(pontos);
    }

	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') or hasAuthority('ROLE_ADMINISTRADOR') and #oauth2.hasScope('common-scope')")
    @GetMapping("/{user_id}/pontos/totalHoras")
    public ResponseEntity<BigDecimal> recuperarTotalHorasTrabalhadasByDate(@PathVariable(value = "user_id") Long id, @RequestBody BuscarPontoDTO datas) {
        BigDecimal totalHoras = service.recuperarTotalHorasTrabalhadasByMonthAndUserId(id, datas);
        return ResponseEntity.ok(totalHoras);
    }
}
