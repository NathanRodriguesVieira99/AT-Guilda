package com.edu.infnet.tp1.presentation.controllers.elastic;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.infnet.tp1.application.services.elastic.LojaGuildaAgregacaoPorCategoriaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/produtos/agregacoes")
@RequiredArgsConstructor
public class LojaGuildaAgregacaoPorCategoriaController {
  private final LojaGuildaAgregacaoPorCategoriaService service;

  @GetMapping("/por-categoria")
  public ResponseEntity<Map<String, Long>> porCategoria() throws IOException {
    return ResponseEntity.ok(service.quantidadePorCategoria());
  }
}
