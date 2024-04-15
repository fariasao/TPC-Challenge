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
@Table(name = "tb_pontos_compra")
public class PontosCompra {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pontoscompraid")
    private Integer pontosCompraID;

    @NotNull (message = "{pontoscompras.compraid.notnull}")
    @ManyToOne
    @JoinColumn(name = "compraid", referencedColumnName = "compraid")
    private Compras compraID;

    @NotNull (message = "{pontoscompras.pointid.notnull}")
    @ManyToOne
    @JoinColumn(name = "pointid", referencedColumnName = "pointid")
    private Pontos pointID;

}
