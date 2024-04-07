package com.TPC.TPC.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_produtos")
public class Produtos {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pdvid")
    private Integer pdvId;

    @NotNull
    @Column(name = "categoriaid")
    private Integer categoriaId;

    @NotBlank
    @Size(max = 255)
    @Column(name = "nome")
    private String nome;

    @NotBlank
    @Size(max = 255)
    @Column(name = "descricao")
    private String descricao;

    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;

    @NotNull
    @Column(name = "ativo")
    private Integer ativo;

    @NotNull
    @Column(name = "tb_comprapontos_pedidoid")
    private Integer compraPontosPedidoId;
}
