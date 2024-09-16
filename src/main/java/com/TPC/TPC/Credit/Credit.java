package com.TPC.TPC.Credit;

import java.math.BigDecimal;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_credit")
public class Credit extends Object{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "creditid")
    private Integer creditID;

    @NotNull (message = "{credit.valor.notnull}")
    @Min(1)
    @Column(name = "valor")
    private BigDecimal valor;

    @NotNull (message = "{credit.datacredito.notnull}")
    @Temporal(TemporalType.DATE)
    @Column(name = "datacredito")
    private Date dataCredito;

    @NotNull (message = "{credit.dataexpiracao.notnull}")
    @Temporal(TemporalType.DATE)
    @Column(name = "dataexpiracao")
    private Date dataExpiracao;

    @NotNull (message = "{credit.utilizado.notnull}")
    @Column(name = "utilizado")
    private char utilizado;
}
