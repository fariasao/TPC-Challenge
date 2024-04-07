package com.TPC.TPC.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categorias")
public class Categorias {

    @Id
    @Column(name = "categoriaid")
    private Integer categoriaId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "pdvid", referencedColumnName = "pdvid")
    private Loja loja;

    @NotBlank
    @Size(max = 50)
    @Column(name = "nome")
    private String nome;

    @NotBlank
    @Size(max = 255)
    @Column(name = "descricao")
    private String descricao;

    @Column(name = "ativo")
    private Integer ativo;
}
