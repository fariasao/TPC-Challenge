package com.TPC.TPC.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data 
@Table(name = "tb_usercluster")
public class UserCluster {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "clusterinfoid")
    private Integer clusterInfoId;

    @NotNull
    @Column(name = "clusterid")
    private Integer clusterId;

    @NotNull
    @Column(name = "userid")
    private Integer userId;
}
