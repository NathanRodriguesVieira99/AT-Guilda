package com.edu.infnet.tp1.presentation.controllers.elastic;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.infnet.tp1.application.services.elastic.LojaGuildaBuscaFuzzyService;
import com.edu.infnet.tp1.presentation.dtos.elastic.ProdutoRetornadoDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/produtos/busca")
@RequiredArgsConstructor
public class LojaGuildaBuscaFuzzyController {
  private final LojaGuildaBuscaFuzzyService service;

  @GetMapping("fuzzy")
  public ResponseEntity<List<ProdutoRetornadoDto>> buscarFuzzy(@RequestParam String termo) throws IOException {
    return ResponseEntity.ok(service.buscarFuzzy(termo));
  }

}
