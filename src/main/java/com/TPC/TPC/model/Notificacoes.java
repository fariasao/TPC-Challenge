package com.TPC.TPC.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_notificacoes")
public class Notificacoes {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mensagemid")
    private Integer mensagemId;

    @NotNull
    @Column(name = "pdvid")
    private Integer pdvId;

    @NotBlank
    @Size(max = 255)
    @Column(name = "titulo")
    private String titulo;

    @Lob
    @NotNull
    @Column(name = "mensagem")
    private String mensagem;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "dataenvio")
    private Date dataEnvio;
}
