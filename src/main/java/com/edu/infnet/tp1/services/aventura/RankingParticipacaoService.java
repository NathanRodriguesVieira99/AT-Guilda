package com.edu.infnet.tp1.services.aventura;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.infnet.tp1.repositories.aventura.AventureiroRepository;
import com.edu.infnet.tp1.repositories.aventura.ParticipacaoMissaoRepository;
import com.edu.infnet.tp1.shared.dtos.RankingParticipacaoDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RankingParticipacaoService {

  private final AventureiroRepository aventureiroRepository;
  private final ParticipacaoMissaoRepository participacaoRepository;

  public List<RankingParticipacaoDto> exec() {
    return aventureiroRepository.findAll().stream()
        .map(aventureiro -> {
          var participacoes = participacaoRepository.findAll().stream()
              .filter(p -> p.getAventureiro().getId().equals(aventureiro.getId()))
              .toList();

          int total = participacoes.size();
          BigDecimal soma = participacoes.stream()
              .map(p -> p.getRecompensaOuro() != null ? p.getRecompensaOuro() : BigDecimal.ZERO)
              .reduce(BigDecimal.ZERO, BigDecimal::add);
          int destaques = (int) participacoes.stream()
              .filter(p -> p.isDestaque())
              .count();

          return new RankingParticipacaoDto(
              aventureiro.getId(),
              aventureiro.getNome(),
              total,
              soma,
              destaques);
        })
        .toList();
  }
}
