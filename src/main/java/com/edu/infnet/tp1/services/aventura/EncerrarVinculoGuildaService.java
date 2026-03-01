package com.edu.infnet.tp1.services.aventura;

import org.springframework.stereotype.Service;

import com.edu.infnet.tp1.models.aventura.Aventureiro;
import com.edu.infnet.tp1.repositories.aventura.AventureiroRepository;
import com.edu.infnet.tp1.shared.exceptions.AventureiroNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EncerrarVinculoGuildaService {

  private final AventureiroRepository aventureiroRepository;

  public Aventureiro exec(Long id) {
    Aventureiro aventureiro = aventureiroRepository.findById(id)
        .orElseThrow(() -> new AventureiroNotFoundException());

    if (aventureiro.isAtivo()) {
      aventureiro.setAtivo(false);
      return aventureiroRepository.save(aventureiro);
    }

    throw new AventureiroNotFoundException();
  }
}
