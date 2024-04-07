package com.TPC.TPC.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_compras")
public class Compras {

    @Id
    @Column(name = "compraid")
    private Integer compraId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private Users user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "pdvid", referencedColumnName = "pdvid")
    private Loja loja;

    @NotNull
    @Column(name = "valor")
    private BigDecimal valor;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "datacompra")
    private Date dataCompra;
}
