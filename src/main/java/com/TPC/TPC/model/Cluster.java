package com.TPC.TPC.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Clob;

@Entity
@Table(name = "tb_cluster")
public class Cluster {

    @Id
    @Column(name = "clusterid")
    private Integer clusterId;

    @NotBlank
    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @NotNull
    @Lob
    @Column(name = "descricao")
    private Clob descricao;
}
