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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_credit")
public class Credit {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "creditid")
    private Integer creditId;

    @NotNull
    @Column(name = "compraid")
    private Integer compraId;

    @NotNull
    @Min(1)
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
