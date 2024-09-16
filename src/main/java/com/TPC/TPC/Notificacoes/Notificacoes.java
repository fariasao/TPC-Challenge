package com.TPC.TPC.Notificacoes;

import java.util.Date;

import com.TPC.TPC.Loja.Loja;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@Table(name = "tb_notificacoes")
public class Notificacoes extends Object{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificacoesid")
    private Integer notificacoesID;

    @NotNull (message = "{notificacoes.pdvid.notnull}")
    @ManyToOne
    @JoinColumn(name = "pdvid", referencedColumnName = "pdvid")
    private Loja pdvID;

    @NotBlank (message = "{notificacoes.titulo.notnull}")
    @Size(max = 255, message = "{notificacoes.titulo.size}")
    @Column(name = "titulo")
    private String titulo;

    @NotNull (message = "{notificacoes.mensagem.notnull}")
    @Column(name = "mensagem")
    private String mensagem;

    @NotNull (message = "{notificacoes.dataenvio.notnull}")
    @Temporal(TemporalType.DATE)
    @Column(name = "dataenvio")
    private Date dataEnvio;
}
