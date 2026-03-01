package com.edu.infnet.tp1.controllers.aventura;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.infnet.tp1.models.aventura.Companheiro;
import com.edu.infnet.tp1.services.aventura.DefinirCompanheiroService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/companheiros")
@RequiredArgsConstructor
public class DefinirCompanheiroController {

  private final DefinirCompanheiroService definirCompanheiroService;

  @PostMapping("/create/{id}")
  public ResponseEntity<?> definirCompanheiro(@PathVariable Long id, @RequestBody Companheiro companheiro) {
    Companheiro companheiroCriado = definirCompanheiroService.exec(id, companheiro);

    return ResponseEntity.status(HttpStatus.CREATED).body(companheiroCriado);

  }
}
