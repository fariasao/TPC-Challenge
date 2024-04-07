package com.TPC.TPC.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_pontos")
public class Pontos {
    
    @Id
    @Column(name = "pointid")
    private Integer pointId;

    @NotNull
    @Column(name = "compraid")
    private Integer compraId;

    @NotNull
    @Column(name = "valor")
    private Integer valor;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "datacredito")
    private Date dataCredito;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "dataexpiracao")
    private Date dataExpiracao;

    @NotNull
    @Column(name = "utilizado")
    private Integer utilizado;
}
