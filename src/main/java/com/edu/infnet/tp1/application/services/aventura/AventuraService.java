package com.edu.infnet.tp1.application.services.aventura;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.edu.infnet.tp1.domain.models.aventura.Aventureiro;
import com.edu.infnet.tp1.infrastructure.repositories.aventura.AventureiroRepository;
import com.edu.infnet.tp1.infrastructure.repositories.aventura.ParticipacaoMissaoRepository;
import com.edu.infnet.tp1.presentation.dtos.AtualizarAventureiroRequestDto;
import com.edu.infnet.tp1.presentation.dtos.AventureiroDetalhesDto;
import com.edu.infnet.tp1.presentation.dtos.CompanheiroResponseDto;
import com.edu.infnet.tp1.presentation.dtos.PaginationQueryDto;
import com.edu.infnet.tp1.shared.exceptions.AventureiroInvalidParamsException;
import com.edu.infnet.tp1.shared.exceptions.AventureiroNotFoundException;
import com.edu.infnet.tp1.shared.exceptions.InvalidQueryParamException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AventuraService {
  private final AventureiroRepository aventureiroRepository;
  private final ParticipacaoMissaoRepository participacaoMissaoRepository;

  public List<Aventureiro> buscarAventureiroPorNome(String nome, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    return aventureiroRepository.findByNomeContaining(nome, pageable).getContent();
  }

  public Aventureiro atualizarAventureiro(Long id, AtualizarAventureiroRequestDto aventureiroAtualizado) {
    Aventureiro aventureiro = aventureiroRepository.findById(id)
        .orElseThrow(() -> new AventureiroNotFoundException());

    if (aventureiroAtualizado.nome() != null && !aventureiroAtualizado.nome().isEmpty())
      aventureiro.setNome(aventureiroAtualizado.nome());

    if (aventureiroAtualizado.classe() != null)
      aventureiro.setClasse(aventureiroAtualizado.classe());

    if (aventureiroAtualizado.nivel() != null)
      aventureiro.setNivel(aventureiroAtualizado.nivel().intValue());

    return aventureiroRepository.save(aventureiro);
  }

  public Aventureiro buscarAventureiroPorId(Long id) {
    return aventureiroRepository.findById(id).orElseThrow(() -> new AventureiroNotFoundException());
  }

  public Aventureiro encerrarVinculoGuilda(Long id) {
    Aventureiro aventureiro = aventureiroRepository.findById(id)
        .orElseThrow(() -> new AventureiroNotFoundException());

    if (aventureiro.isAtivo()) {
      aventureiro.setAtivo(false);
      return aventureiroRepository.save(aventureiro);
    }

    throw new AventureiroNotFoundException();
  }

  public List<Aventureiro> listarAventureiros(PaginationQueryDto params) {
    if (params.page() < 0) {
      throw new InvalidQueryParamException();
    }

    if (params.size() < 1 || params.size() > 50)
      throw new InvalidQueryParamException();

    if (params.ativo() != true) {
      throw new InvalidQueryParamException();
    }

    if (params.nivelMinimo() <= 0) {
      throw new InvalidQueryParamException();
    }

    Pageable pageable = PageRequest.of(params.page(), params.size());

    return aventureiroRepository.findAll(pageable).getContent();
  }

  public int contarAventureiros(PaginationQueryDto params) {
    return (int) aventureiroRepository.count();
  }

  public Aventureiro recrutarNovamente(Long id) {
    Aventureiro aventureiro = aventureiroRepository.findById(id).orElseThrow(() -> new AventureiroNotFoundException());

    if (!aventureiro.isAtivo()) {
      aventureiro.setAtivo(true);
      return aventureiroRepository.save(aventureiro);
    }

    throw new AventureiroNotFoundException();
  }

  public Aventureiro exec(Aventureiro aventureiro) {
    if (aventureiro.getNome() == null || aventureiro.getNome().isBlank())
      throw new AventureiroInvalidParamsException();

    if (aventureiro.getClasse() == null)
      throw new AventureiroInvalidParamsException();

    if (aventureiro.getNivel() == null || aventureiro.getNivel() <= 0)
      throw new AventureiroInvalidParamsException();

    if (aventureiro.getOrganizacao() == null)
      throw new AventureiroInvalidParamsException();

    if (aventureiro.getUsuarioCadastro() == null)
      throw new AventureiroInvalidParamsException();

    aventureiro.setAtivo(true);
    aventureiro.setCompanheiro(null);

    return aventureiroRepository.save(aventureiro);
  }

  public AventureiroDetalhesDto visualizarAventureiroCompleto(Long id) {
    Aventureiro aventureiro = aventureiroRepository.findByIdComCompanheiro(id)
        .orElseThrow(AventureiroNotFoundException::new);

    int totalParticipacoes = (int) participacaoMissaoRepository.countByAventureiroId(id);

    var participacoes = participacaoMissaoRepository.findByAventureiroIdOrderByCreatedAtDesc(id);
    String ultimaMissao = participacoes.isEmpty() ? null : participacoes.get(0).getMissao().getTitulo();

    CompanheiroResponseDto companheiroDto = null;
    if (aventureiro.getCompanheiro() != null) {
      companheiroDto = new CompanheiroResponseDto(
          aventureiro.getCompanheiro().getId(),
          aventureiro.getCompanheiro().getNome(),
          aventureiro.getCompanheiro().getEspecie(),
          aventureiro.getCompanheiro().getLealdade());
    }

    return new AventureiroDetalhesDto(
        aventureiro.getId(),
        aventureiro.getNome(),
        aventureiro.getClasse(),
        aventureiro.getNivel(),
        aventureiro.isAtivo(),
        companheiroDto,
        totalParticipacoes,
        ultimaMissao);
  }
}
