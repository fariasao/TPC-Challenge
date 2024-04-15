package com.TPC.TPC.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_cluster")
public class Cluster {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "clusterid")
    private Integer clusterID;

    @NotBlank (message = "{cluster.name.notnull}")
    @Size(max = 255, message = "{cluster.name.size}")
    @Column(name = "name")
    private String name;

    @NotNull (message = "{cluster.descricao.notnull}")
    @Lob
    @Column(name = "descricao")
    private String descricao;
}
