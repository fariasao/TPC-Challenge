package com.TPC.TPC.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_users")
public class Users {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "nome")
    private String nome;

    @Size(max = 50)
    @Column(name = "sobrenome")
    private String sobrenome;

    @Email
    @NotBlank
    @Size(max = 50)
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(max = 255)
    @Column(name = "password")
    private String password;

    @NotBlank
    @Size(max = 20)
    @Column(name = "telefone")
    private String telefone;

    @NotBlank
    @Size(max = 255)
    @Column(name = "endereco")
    private String endereco;

    @NotBlank
    @Size(max = 255)
    @Column(name = "numero")
    private String numero;

    @Size(max = 255)
    @Column(name = "complemento")
    private String complemento;

    @NotNull
    @Column(name = "active")
    private Integer active;
}
