package com.TPC.TPC.Campanhas;

import com.TPC.TPC.Cluster.Cluster;
import com.TPC.TPC.UserMaster.UserMaster;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_campanhas")
public class Campanhas extends Object{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campanhaid")
    private Integer campanhaID;

    @NotNull (message =  "{campanhas.masterid.notnull}")
    @ManyToOne
    @JoinColumn(name = "masterid", referencedColumnName = "masterid")
    private UserMaster masterID;

    @NotNull (message =  "{campanhas.clusterid.notnull}")
    @ManyToOne
    @JoinColumn(name = "clusterid", referencedColumnName = "clusterid")
    private Cluster clusterID;

    @NotBlank (message =  "{campanhas.titulo.notnull}")
    @Size(max = 255, message = "{campanhas.titulo.size}")
    @Column(name = "titulo")
    private String titulo;

    @Column(name = "conteudo")
    private String conteudo;

    @NotNull (message =  "{campanhas.descricao.notnull}")
    @Column(name = "descricao")
    private String descricao;

    @NotNull (message =  "{campanhas.canaltipo.notnull}")
    @Column(name = "canaltipo")
    private Integer canalTipo;
}
