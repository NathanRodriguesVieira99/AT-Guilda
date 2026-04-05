package com.edu.infnet.tp1.infrastructure.repositories.painelTaticoMissao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.infnet.tp1.domain.models.painelTaticoMissao.PainelTaticoMissao;

public interface PainelTaticoMissaoRepository extends JpaRepository<PainelTaticoMissao, Long> {
  @Query(value = "SELECT * FROM operacoes.vw_painel_tatico_missao " +
      "WHERE ultima_atualizacao >= NOW() - INTERVAL '15 days' " +
      "ORDER BY indice_prontidao DESC " +
      "LIMIT 10", nativeQuery = true)
  List<PainelTaticoMissao> buscarTop10NosUltimos15Dias();
}
