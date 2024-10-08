package com.TPC.TPC.Pontos;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_pontos")
public class Pontos extends Object{
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pointid")
    private Integer pointID;

    @NotNull (message = "{pontos.valor.notnull}")
    @Column(name = "valor")
    private Integer valor;

    @NotNull (message = "{pontos.datacreditado.notnull}")
    @Temporal(TemporalType.DATE)
    @Column(name = "datacreditado")
    private Date dataCreditado;

    @NotNull (message = "{pontos.dataexpiracao.notnull}")
    @Temporal(TemporalType.DATE)
    @Column(name = "dataexpirado")
    private Date dataExpirado;

    @NotNull (message = "{pontos.utilizado.notnull}")
    @Column(name = "utilizado")
    private char utilizado;
}
