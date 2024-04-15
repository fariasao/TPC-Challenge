package com.TPC.TPC.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_categorias")
public class Categorias {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "categoriaid")
    private Integer categoriaID;

    @NotBlank (message =  "{categorias.nome.notnull}")
    @Size(max = 50, message = "{categorias.nome.size}")
    @Column(name = "nome")
    private String nome;

    @NotBlank (message =  "{categorias.descricao.notnull}")
    @Size(max = 255, message = "{categorias.descricao.size}")
    @Column(name = "descricao")
    private String descricao;

    @Column(name = "ativo")
    private boolean ativo;
}
