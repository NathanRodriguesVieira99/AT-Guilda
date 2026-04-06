package com.edu.infnet.tp1.domain.models.elastic;

import java.math.BigDecimal;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "guilda_loja")
public class LojaGuildaDocument {
  private String nome;
  private String categoria;
  private String descricao;
  private BigDecimal preco;
  private String raridade;
}
