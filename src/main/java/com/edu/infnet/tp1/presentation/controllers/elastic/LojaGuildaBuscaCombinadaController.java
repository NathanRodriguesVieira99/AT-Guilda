package com.edu.infnet.tp1.presentation.controllers.elastic;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.infnet.tp1.application.services.elastic.LojaGuildaBuscaCombinadaService;
import com.edu.infnet.tp1.presentation.dtos.elastic.ProdutoRetornadoDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/produtos/busca")
@RequiredArgsConstructor
public class LojaGuildaBuscaCombinadaController {
  private final LojaGuildaBuscaCombinadaService service;

  @GetMapping("/avancada")
  public ResponseEntity<List<ProdutoRetornadoDto>> buscaAvancada(@RequestParam String raridade,
      @RequestParam String categoria, @RequestParam BigDecimal min, @RequestParam BigDecimal max) throws IOException {
    return ResponseEntity.ok(service.buscaCombinada(raridade, categoria, min, max));
  }

}
