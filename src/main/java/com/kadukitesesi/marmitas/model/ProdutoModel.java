package com.kadukitesesi.marmitas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal preco;
    @ManyToMany(mappedBy = "produtos")
    private List<PedidoModel> pedidos;

    public ProdutoModel(String nome, BigDecimal preco) {

    this.nome = nome;
    this.preco = preco;
    }
}
