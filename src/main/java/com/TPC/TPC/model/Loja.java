package com.TPC.TPC.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_loja")
public class Loja {
    
    @Id
    @Column(name = "pdvid")
    private Integer pdvId;

    @NotBlank
    @Size(max = 255)
    @Column(name = "nomeloja")
    private String nomeLoja;

    @NotBlank
    @Size(max = 255)
    @Column(name = "endereco")
    private String endereco;

    @NotNull
    @Column(name = "numero")
    private Integer numero;

    @Size(max = 50)
    @Column(name = "complemento")
    private String complemento;

    @Size(max = 50)
    @Column(name = "cep")
    private String cep;

    @Column(name = "active")
    private Integer active;
}
