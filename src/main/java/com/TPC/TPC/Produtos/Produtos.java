package com.TPC.TPC.Produtos;

import java.math.BigDecimal;

import com.TPC.TPC.Categorias.Categorias;
import com.TPC.TPC.Loja.Loja;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_produtos")
public class Produtos extends Object{
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "produtoid")
    private Integer produtoID;

    @NotNull (message = "{produtos.pdvid.notnull}")
    @ManyToOne
    @JoinColumn(name = "pdvid", referencedColumnName = "pdvid")
    private Loja pdvID;
    
    @NotNull (message = "{produtos.categoriaid.notnull}")
    @ManyToOne
    @JoinColumn(name = "categoriaid", referencedColumnName = "categoriaid")
    private Categorias categoriaID;

    @NotBlank (message = "{produtos.nome.notnull}")
    @Size(max = 255, message = "{produtos.nome.size}")
    @Column(name = "nome")
    private String nome;

    @NotBlank (message = "{produtos.descricao.notnull}")
    @Size(max = 255, message = "{produtos.descricao.size}")
    @Column(name = "descricao")
    private String descricao;

    @NotNull (message = "{produtos.valor.notnull}")
    @Column(name = "valor", precision = 15, scale = 2)
    private BigDecimal valor;

    @NotNull (message = "{produtos.ativo.notnull}")
    @Column(name = "ativo")
    private char ativo;
}
