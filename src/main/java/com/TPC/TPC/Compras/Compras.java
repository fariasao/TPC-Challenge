package com.TPC.TPC.Compras;

import java.math.BigDecimal;
import java.util.Date;

import com.TPC.TPC.Loja.Loja;
import com.TPC.TPC.Users.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tb_compras")
public class Compras extends Object{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "compraid")
    private Integer compraID;

    @NotNull (message = "{compras.usersid.notnull}")
    @ManyToOne
    @JoinColumn(name = "usersid", referencedColumnName = "usersid")
    private Users usersID;

    @NotNull (message = "{compras.pdvid.notnull}")
    @ManyToOne
    @JoinColumn(name = "pdvid", referencedColumnName = "pdvid")
    private Loja pdvID;

    @NotNull(message = "{compras.valor.notnull}")
    @Column(name = "valor", precision = 15, scale = 2)
    private BigDecimal valor;

    @NotNull (message = "{compras.datacompra.notnull}")
    @Temporal(TemporalType.DATE)
    @Column(name = "datacompra")
    private Date dataCompra;
}
