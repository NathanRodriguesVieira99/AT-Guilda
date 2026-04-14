package com.edu.infnet.tp1.presentation.controllers.elastic;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.infnet.tp1.application.services.elastic.LojaGuildaBuscaService;
import com.edu.infnet.tp1.presentation.dtos.elastic.ProdutoRetornadoDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/produtos/busca")
@RequiredArgsConstructor
public class LojaGuildaBuscaController {
  private final LojaGuildaBuscaService lojaGuildaBuscaService;

  @GetMapping("/avancada")
  public ResponseEntity<List<ProdutoRetornadoDto>> buscaAvancada(
      @RequestParam String raridade,
      @RequestParam String categoria,
      @RequestParam BigDecimal min,
      @RequestParam BigDecimal max) throws IOException {
    return ResponseEntity.ok(lojaGuildaBuscaService.buscaCombinada(raridade, categoria, min, max));
  }

  @GetMapping("com-filtro")
  public ResponseEntity<List<ProdutoRetornadoDto>> buscarPorNome(
      @RequestParam String termo,
      @RequestParam String categoria) throws IOException {
    return ResponseEntity.ok(lojaGuildaBuscaService.buscarComFiltros(termo, categoria));
  }

  @GetMapping("faixa-preco")
  public ResponseEntity<List<ProdutoRetornadoDto>> buscarFuzzy(
      @RequestParam BigDecimal min,
      @RequestParam BigDecimal max) throws IOException {
    return ResponseEntity.ok(lojaGuildaBuscaService.buscarFaixaPreco(min, max));
  }

  @GetMapping("fuzzy")
  public ResponseEntity<List<ProdutoRetornadoDto>> buscarFuzzy(@RequestParam String termo) throws IOException {
    return ResponseEntity.ok(lojaGuildaBuscaService.buscarFuzzy(termo));
  }

  @GetMapping("multicampos")
  public ResponseEntity<List<ProdutoRetornadoDto>> buscarMulticampo(@RequestParam String termo) throws IOException {
    return ResponseEntity.ok(lojaGuildaBuscaService.buscarMulticampo(termo));
  }

  @GetMapping("descricao")
  public ResponseEntity<List<ProdutoRetornadoDto>> buscarPorDescricao(@RequestParam String termo) throws IOException {
    return ResponseEntity.ok(lojaGuildaBuscaService.buscarPorDescricao(termo));
  }

  @GetMapping("frase")
  public ResponseEntity<List<ProdutoRetornadoDto>> buscarPorFraseExata(@RequestParam String termo) throws IOException {
    return ResponseEntity.ok(lojaGuildaBuscaService.buscarPorFraseExata(termo));
  }

  @GetMapping("nome")
  public ResponseEntity<List<ProdutoRetornadoDto>> buscarPorNome(@RequestParam String termo) throws IOException {
    return ResponseEntity.ok(lojaGuildaBuscaService.buscarPorNome(termo));
  }

}
