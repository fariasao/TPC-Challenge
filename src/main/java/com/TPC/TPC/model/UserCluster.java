package com.TPC.TPC.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_usercluster")
public class UserCluster {
    
    @Id
    @Column(name = "clusterinfoid")
    private Integer clusterInfoId;

    @NotNull
    @Column(name = "clusterid")
    private Integer clusterId;

    @NotNull
    @Column(name = "userid")
    private Integer userId;
}
