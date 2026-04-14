package com.edu.infnet.tp1.presentation.controllers.aventura;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.infnet.tp1.application.services.aventura.AventuraService;
import com.edu.infnet.tp1.domain.models.aventura.Aventureiro;
import com.edu.infnet.tp1.presentation.dtos.AtualizarAventureiroRequestDto;
import com.edu.infnet.tp1.presentation.dtos.AventureiroDetalhesDto;
import com.edu.infnet.tp1.presentation.dtos.PaginationQueryDto;
import com.edu.infnet.tp1.presentation.dtos.PaginationResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/aventureiros")
@RequiredArgsConstructor
public class AventuraController {
  private final AventuraService aventuraService;

  @PatchMapping("/{id}")
  public ResponseEntity<?> atualizarDadosAventureiro(
      @PathVariable Long id,
      @RequestBody AtualizarAventureiroRequestDto aventureiroAtualizado) {
    Aventureiro resultado = aventuraService.atualizarAventureiro(id, aventureiroAtualizado);
    return ResponseEntity.ok().body(resultado);
  }

  @GetMapping("/buscar")
  public ResponseEntity<List<Aventureiro>> buscar(
      @RequestParam String nome,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    return ResponseEntity.ok(aventuraService.buscarAventureiroPorNome(nome, page, size));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> buscarAventureiroPorId(@PathVariable Long id) {
    Aventureiro aventureiro = aventuraService.buscarAventureiroPorId(id);
    return ResponseEntity.status(HttpStatus.OK).body(aventureiro);
  }

  @PatchMapping("/remove/{id}")
  public ResponseEntity<?> encerrarVinculoGuilda(@PathVariable Long id) {
    Aventureiro aventureiroAtivo = aventuraService.encerrarVinculoGuilda(id);
    return ResponseEntity.status(HttpStatus.OK).body(aventureiroAtivo);
  }

  @GetMapping()
  public ResponseEntity<?> listarAventureiros(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(required = false) String classe,
      @RequestParam(required = false) Boolean ativo,
      @RequestParam(required = false) Integer nivelMinimo) {
    PaginationQueryDto params = new PaginationQueryDto(page, size, classe, ativo, nivelMinimo);

    List<Aventureiro> resultado = aventuraService.listarAventureiros(params);

    List<PaginationResponseDto> result = resultado.stream()
        .map(aventureiro -> new PaginationResponseDto(
            aventureiro.getId(),
            aventureiro.getNome(),
            aventureiro.getClasse(),
            aventureiro.getNivel(),
            aventureiro.isAtivo(),
            aventureiro.getCompanheiro()))
        .toList();

    int totalAventureiros = aventuraService.contarAventureiros(params);

    int totalPages = (int) Math.ceil(totalAventureiros / (double) size);

    HttpHeaders headers = new HttpHeaders();
    headers.add("X-Total-Count", String.valueOf(totalAventureiros));
    headers.add("X-Page", String.valueOf(page));
    headers.add("X-Size", String.valueOf(size));
    headers.add("X-Total-Pages", String.valueOf(totalPages));

    return ResponseEntity.ok().headers(headers).body(result);
  }

  @GetMapping("/{id}/detalhes")
  public ResponseEntity<AventureiroDetalhesDto> detalhesAventureiro(
      @PathVariable Long id) {
    var resultado = aventuraService.visualizarAventureiroCompleto(id);
    return ResponseEntity.ok(resultado);
  }

  @PatchMapping("/recruit/{id}")
  public ResponseEntity<?> recrutarNovamente(@PathVariable Long id) {
    Aventureiro aventureiroInativo = aventuraService.recrutarNovamente(id);
    return ResponseEntity.status(HttpStatus.OK).body(aventureiroInativo);
  }

  @PostMapping("/create")
  public ResponseEntity<?> registrarAventureiro(@RequestBody Aventureiro aventureiro) {
    Aventureiro novoAventureiro = aventuraService.exec(aventureiro);
    return ResponseEntity.status(HttpStatus.CREATED).body(novoAventureiro);
  }
}
