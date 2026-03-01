package com.edu.infnet.tp1.controllers.aventura;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.infnet.tp1.services.aventura.VisualizacaoAventureiroCompletaService;
import com.edu.infnet.tp1.shared.dtos.AventureiroDetalhesDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/aventureiros")
@RequiredArgsConstructor
public class VisualizacaoAventureiroCompletaController {

  private final VisualizacaoAventureiroCompletaService service;

  @GetMapping("/{id}/detalhes")
  public ResponseEntity<AventureiroDetalhesDto> detalhes(@PathVariable Long id) {
    var resultado = service.exec(id);
    return ResponseEntity.ok(resultado);
  }
}
