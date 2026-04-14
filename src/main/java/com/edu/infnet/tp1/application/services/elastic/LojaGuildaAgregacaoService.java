package com.edu.infnet.tp1.application.services.elastic;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.aggregations.AggregationRange;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LojaGuildaAgregacaoService {
  private final ElasticsearchClient client;

  public Map<String, Long> quantidadePorCategoria() throws IOException {
    SearchResponse<Void> response = client.search(
        s -> s.index("guilda_loja")
            .size(0)
            .aggregations("por_categoria", a -> a.terms(t -> t.field("categoria"))),
        Void.class);
    return response.aggregations()
        .get("por_categoria")
        .sterms()
        .buckets().array().stream()
        .collect(Collectors.toMap(
            b -> b.key().stringValue(),
            b -> b.docCount()));
  }

  public Map<String, Long> buscarFaixasDePreco() throws IOException {
    SearchResponse<Void> response = client.search(
        s -> s.index("guilda_loja")
            .size(0)
            .aggregations("faixas_preco",
                a -> a.range(f -> f.field("preco").ranges(
                    AggregationRange.of(r -> r.to(100.0)),
                    AggregationRange.of(r -> r.from(100.0).to(300.0)),
                    AggregationRange.of(r -> r.from(300.0).to(700.0)),
                    AggregationRange.of(r -> r.from(700.0))))),
        Void.class);

    return response.aggregations().get("faixas_preco").range().buckets().array().stream()
        .collect(Collectors.toMap(r -> {
          if (r.to() == null)
            return "Acima de 700";
          if (r.from() == null)
            return "Abaixo de 100";
          if (r.from() == 100.0 && r.to() == 300.0)
            return "De 100 a 300";
          if (r.from() == 300.0 && r.to() == 700.0)
            return "De 300 a 700";
          return r.key();
        },
            r -> r.docCount()));
  }

  public Double buscarPrecoMedioProdutos() throws IOException {
    SearchResponse<Void> response = client.search(
        s -> s.index("guilda_loja")
            .size(0)
            .aggregations("preco_medio", a -> a.avg(t -> t.field("preco"))),
        Void.class);

    double valorArredondado = Math.round(response.aggregations()
        .get("preco_medio")
        .avg()
        .value() * 100.0) / 100.0;

    return valorArredondado;
  }

  public Map<String, Long> quantidadePorRaridade() throws IOException {
    SearchResponse<Void> response = client.search(
        s -> s.index("guilda_loja")
            .size(0)
            .aggregations("por_raridade", a -> a.terms(t -> t.field("raridade"))),
        Void.class);

    return response.aggregations()
        .get("por_raridade")
        .sterms()
        .buckets().array().stream()
        .collect(Collectors.toMap(
            b -> b.key().stringValue(),
            b -> b.docCount()));
  }
}
