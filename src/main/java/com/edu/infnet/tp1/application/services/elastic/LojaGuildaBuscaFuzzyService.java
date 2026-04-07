package com.edu.infnet.tp1.application.services.elastic;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.infnet.tp1.domain.models.elastic.LojaGuildaDocument;
import com.edu.infnet.tp1.presentation.dtos.elastic.ProdutoRetornadoDto;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LojaGuildaBuscaFuzzyService {
  private final ElasticsearchClient client;

  public List<ProdutoRetornadoDto> buscarFuzzy(String termo) throws IOException {
    /*
      O .fuzzy() não estava funcionando pois ele busca apenas por apenas uma
      palavara, então usei o .match() com o .fuzziness("AUTO").
      
      Antes ao escrever 'Machodo' por exemplo não retornava nada, agora retorna todos os nomes com 'Machado'
     */
    SearchResponse<LojaGuildaDocument> response = client.search(
        s -> s.index("guilda_loja")
            .query(q -> q.match(m -> m.field("nome")
                .query(termo)
                .fuzziness("AUTO"))),
        LojaGuildaDocument.class);

    return toDtoList(response);
  }

  private List<ProdutoRetornadoDto> toDtoList(SearchResponse<LojaGuildaDocument> response) {
    return response.hits()
        .hits()
        .stream()
        .map(ProdutoRetornadoDto::toDTO)
        .toList();
  }
}
