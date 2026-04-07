package com.edu.infnet.tp1.presentation.controllers.elastic;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.infnet.tp1.application.services.elastic.LojaGuildaBuscaComFiltrosService;
import com.edu.infnet.tp1.presentation.dtos.elastic.ProdutoRetornadoDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/produtos/busca")
@RequiredArgsConstructor
public class LojaGuildaBuscaComFiltrosController {
  private final LojaGuildaBuscaComFiltrosService service;

  @GetMapping("com-filtro")
  public ResponseEntity<List<ProdutoRetornadoDto>> buscarPorNome(@RequestParam String termo,@RequestParam String categoria) throws IOException {
    return ResponseEntity.ok(service.buscarComFiltros(termo,categoria));
  }

}
