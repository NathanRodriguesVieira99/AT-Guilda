package com.edu.infnet.tp1.controllers.aventura;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.infnet.tp1.models.aventura.Aventureiro;
import com.edu.infnet.tp1.services.aventura.BuscarAventureiroPorIdService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/aventureiros")
@RequiredArgsConstructor
public class BuscarAventureiroPorIdController {

  private final BuscarAventureiroPorIdService buscarAventureirosPorIdService;

  @GetMapping("/{id}")
  public ResponseEntity<?> listarAventureiros(@PathVariable Long id) {

    Aventureiro aventureiro = buscarAventureirosPorIdService.exec(id);

    return ResponseEntity.status(HttpStatus.OK).body(aventureiro);

  }
}
