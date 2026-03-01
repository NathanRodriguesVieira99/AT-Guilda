package com.edu.infnet.tp1.controllers.aventura;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.infnet.tp1.models.aventura.Missao;
import com.edu.infnet.tp1.services.aventura.ListarMissoesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/missoes")
@RequiredArgsConstructor
public class ListarMissoesController {

  private final ListarMissoesService service;

  @GetMapping
  public ResponseEntity<List<Missao>> listar(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    return ResponseEntity.ok(service.exec(page, size));
  }
}
