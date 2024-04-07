package com.TPC.TPC.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_userpdv")
public class UserPDV {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "atuserid")
    private Integer atUserId;

    @NotNull
    @Column(name = "pdvid")
    private Integer pdvId;

    @NotBlank
    @Size(max = 255)
    @Column(name = "nome")
    private String nome;

    @NotBlank
    @Size(max = 255)
    @Column(name = "sobrenome")
    private String sobrenome;

    @Email
    @NotBlank
    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(max = 255)
    @Column(name = "password")
    private String password;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "dataregistro")
    private Date dataRegistro;

    @NotNull
    @Column(name = "ativo")
    private Integer ativo;
}
