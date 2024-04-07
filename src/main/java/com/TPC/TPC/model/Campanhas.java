package com.TPC.TPC.model;

import java.sql.Clob;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_campanhas")
public class Campanhas {
    
    @Id
    @Column(name = "campanhaid")
    private Integer campanhaId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "masterid", referencedColumnName = "masterid")
    private UserMaster masterId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "cluesterid", referencedColumnName = "clusterid")
    private Cluster clusterId;

    @NotBlank
    @Size(max = 255)
    @Column(name = "titulo")
    private String titulo;

    @Lob
    @Column(name = "conteudo")
    private Clob conteudo;

    @NotNull
    @Lob
    @Column(name = "descricao")
    private Clob descricao;

    @NotNull
    @Column(name = "canaltipo")
    private Integer canalTipo;
}
