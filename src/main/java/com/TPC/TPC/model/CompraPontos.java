package com.TPC.TPC.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_comprapontos")
public class CompraPontos {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pedidoid")
    private Integer pedidoId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "pdvid", referencedColumnName = "pdvid")
    private Loja loja;

    @NotNull
    @Column(name = "itemid")
    private Integer itemId;

    @NotNull
    @Column(name = "quantidade")
    private Integer quantidade;
}
