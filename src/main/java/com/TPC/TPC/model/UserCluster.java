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
@Table(name = "tb_user_cluster")
public class UserCluster {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userclusterid")
    private Integer userClusterID;

    @NotNull (message = "{usercluster.clusterid.notnull}")
    @ManyToOne
    @JoinColumn(name = "clusterid", referencedColumnName = "clusterid")
    private Cluster clusterID;

    @NotNull (message = "{usercluster.usersid.notnull}")
    @ManyToOne
    @JoinColumn(name = "usersid", referencedColumnName = "usersid")
    private Users usersID;
}
