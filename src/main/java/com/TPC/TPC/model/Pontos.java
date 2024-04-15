package com.TPC.TPC.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_pontos")
public class Pontos {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pointid")
    private Integer pointID;

    @NotNull (message = "{pontos.valor.notnull}")
    @Column(name = "valor")
    private Integer valor;

    @NotNull (message = "{pontos.datacredito.notnull}")
    @Temporal(TemporalType.DATE)
    @Column(name = "datacredito")
    private Date dataCredito;

    @NotNull (message = "{pontos.dataexpiracao.notnull}")
    @Temporal(TemporalType.DATE)
    @Column(name = "dataexpiracao")
    private Date dataExpiracao;

    @NotNull (message = "{pontos.utilizado.notnull}")
    @Column(name = "utilizado")
    private boolean utilizado;
}
