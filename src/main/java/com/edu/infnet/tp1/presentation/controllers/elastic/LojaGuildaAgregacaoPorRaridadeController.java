package com.edu.infnet.tp1.presentation.controllers.elastic;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.infnet.tp1.application.services.elastic.LojaGuildaAgregacaoPorRaridadeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/produtos/agregacoes")
@RequiredArgsConstructor
public class LojaGuildaAgregacaoPorRaridadeController {
  private final LojaGuildaAgregacaoPorRaridadeService service;

  @GetMapping("/por-raridade")
  public ResponseEntity<Map<String, Long>> porRaridade() throws IOException {
    return ResponseEntity.ok(service.quantidadePorRaridade());
  }
}
