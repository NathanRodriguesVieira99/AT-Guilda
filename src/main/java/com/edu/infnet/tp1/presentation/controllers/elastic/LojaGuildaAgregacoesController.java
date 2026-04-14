package com.edu.infnet.tp1.presentation.controllers.elastic;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.infnet.tp1.application.services.elastic.LojaGuildaAgregacaoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/produtos/agregacoes")
@RequiredArgsConstructor
public class LojaGuildaAgregacoesController {
  private final LojaGuildaAgregacaoService lojaGuildaAgregacaoService;

  @GetMapping("/por-categoria")
  public ResponseEntity<Map<String, Long>> porCategoria() throws IOException {
    return ResponseEntity.ok(lojaGuildaAgregacaoService.quantidadePorCategoria());
  }

  @GetMapping("/faixas-preco")
  public ResponseEntity<Map<String, Long>> faixasDePreco() throws IOException {
    return ResponseEntity.ok(lojaGuildaAgregacaoService.buscarFaixasDePreco());
  }

  @GetMapping("/preco-medio")
  public ResponseEntity<Double> precoMedio() throws IOException {
    return ResponseEntity.ok(lojaGuildaAgregacaoService.buscarPrecoMedioProdutos());
  }

  @GetMapping("/por-raridade")
  public ResponseEntity<Map<String, Long>> porRaridade() throws IOException {
    return ResponseEntity.ok(lojaGuildaAgregacaoService.quantidadePorRaridade());
  }
}
