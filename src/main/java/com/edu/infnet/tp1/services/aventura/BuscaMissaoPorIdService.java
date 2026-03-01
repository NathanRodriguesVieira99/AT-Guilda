package com.edu.infnet.tp1.services.aventura;

import org.springframework.stereotype.Service;

import com.edu.infnet.tp1.models.aventura.Missao;
import com.edu.infnet.tp1.repositories.aventura.MissaoRepository;
import com.edu.infnet.tp1.shared.exceptions.MissaoNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BuscaMissaoPorIdService {

  private final MissaoRepository missaoRepository;

  public Missao exec(Long id) {
    return missaoRepository.findById(id)
        .orElseThrow(MissaoNotFoundException::new);
  }
}
