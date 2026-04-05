package com.edu.infnet.tp1.presentation.controllers.painelTaticoMissao;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.infnet.tp1.application.services.painelTaticoMissao.PainelTaticoMissaoService;
import com.edu.infnet.tp1.domain.models.painelTaticoMissao.PainelTaticoMissao;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/missoes")
@RequiredArgsConstructor
public class PainelTaticoMissaoController {
  private final PainelTaticoMissaoService service;

  @GetMapping("/top15dias")
  public List<PainelTaticoMissao> getTopMissoesUltimos15Dias() {
    return service.getTopMissoesNosUltimos15Dias();
  }

}
