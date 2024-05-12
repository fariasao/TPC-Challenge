package com.TPC.TPC.config;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.TPC.TPC.model.Campanhas;
import com.TPC.TPC.model.Categorias;
import com.TPC.TPC.model.Cluster;
import com.TPC.TPC.model.Compras;
import com.TPC.TPC.model.Loja;
import com.TPC.TPC.model.Notificacoes;
import com.TPC.TPC.model.Pontos;
import com.TPC.TPC.model.Produtos;
import com.TPC.TPC.model.UserMaster;
import com.TPC.TPC.model.Users;
import com.TPC.TPC.repository.CampanhasRepository;
import com.TPC.TPC.repository.CategoriasRepository;
import com.TPC.TPC.repository.ClusterRepository;
import com.TPC.TPC.repository.ComprasRepository;
import com.TPC.TPC.repository.LojaRepository;
import com.TPC.TPC.repository.NotificacoesRepository;
import com.TPC.TPC.repository.PontosRepository;
import com.TPC.TPC.repository.ProdutosRepository;
import com.TPC.TPC.repository.UserMasterRepository;
import com.TPC.TPC.repository.UsersRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner{
    @Autowired
    CampanhasRepository campanhasRepository;

    @Autowired
    CategoriasRepository categoriasRepository;

    @Autowired
    ComprasRepository comprasRepository;

    @Autowired
    NotificacoesRepository notificacoesRepository;

    @Autowired
    PontosRepository pontosRepository;

    @Autowired
    ProdutosRepository produtosRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UserMasterRepository userMasterRepository;

    @Autowired
    LojaRepository lojaRepository;

    @Autowired
    ClusterRepository clusterRepository;

    @Override
    public void run(String... args) throws Exception {
        lojaRepository.saveAll(
            List.of(
                Loja.builder()
                    .pdvID(1)
                    .nomeLoja("Thiago Eletrônicos")
                    .endereco("Rua 20 de Maio")
                    .numero(1100)
                    .complemento("A")
                    .cep("01234-567")
                    .active(true)
                    .build(),
                Loja.builder().pdvID(2)
                    .nomeLoja("Guilherme Tech")
                    .endereco("Rua 20 de Maio")
                    .numero(1100)
                    .complemento("B")
                    .cep("01234-567")
                    .active(true)
                    .build()
            )
        );

        userMasterRepository.saveAll(
            List.of(
                UserMaster.builder()
                    .masterID(1)
                    .nome("Beatriz")
                    .sobrenome("Lucas")
                    .email("beatriz.lucas@fiap.com.br")
                    .password("123456")
                    .dataRegistro(Date.from(Instant.now()))
                    .ativo(true)
                    .build(),
                UserMaster.builder()
                    .masterID(2)
                    .nome("Enzo")
                    .sobrenome("Farias")
                    .email("enzo.farias@fiap.com.br")
                    .password("123456")
                    .dataRegistro(Date.from(Instant.now().minusSeconds(100000)))
                    .ativo(true)
                    .build()
            )
        );

        clusterRepository.saveAll(
            List.of(
                Cluster.builder()
                    .clusterID(1)
                    .name("Cluster 1")
                    .descricao("Cluster 1")
                    .build(),
                Cluster.builder()
                    .clusterID(2)
                    .name("Cluster 2")
                    .descricao("Cluster 2")
                    .build()
            )
        );

        campanhasRepository.saveAll(
            List.of(
                Campanhas.builder()
                    .campanhaID(1)
                    .masterID(userMasterRepository.findById(1).get())
                    .clusterID(clusterRepository.findById(1).get())
                    .titulo("Tech Week")
                    .conteudo("Campanha 1")
                    .descricao("Campanha 1")
                    .canalTipo(1)
                    .build(),
                Campanhas.builder()
                    .campanhaID(2)
                    .masterID(userMasterRepository.findById(2).get())
                    .clusterID(clusterRepository.findById(2).get())
                    .titulo("Saúde e Bem-Estar")
                    .conteudo("Campanha 2")
                    .descricao("Campanha 2")
                    .canalTipo(2)
                    .build()
            )
        );
        
        categoriasRepository.saveAll(
            List.of(
                Categorias.builder()
                    .categoriaID(1)
                    .nome("Tecnologia")
                    .descricao("Categoria 1")
                    .ativo(true)
                    .build(),
                Categorias.builder()
                    .categoriaID(2)
                    .nome("Farmácia")
                    .descricao("Categoria 2")
                    .ativo(true)
                    .build()
            )
        );

        notificacoesRepository.saveAll(
            List.of(
                Notificacoes.builder()
                    .notificacoesID(1)
                    .pdvID(lojaRepository.findById(1).get())
                    .titulo("Notificação 1")
                    .mensagem("Notificação 1")
                    .dataEnvio(Date.from(Instant.now()))
                    .build(),
                Notificacoes.builder()
                    .notificacoesID(2)
                    .pdvID(lojaRepository.findById(2).get())
                    .titulo("Notificação 2")
                    .mensagem("Notificação 2")
                    .dataEnvio(Date.from(Instant.now().minusSeconds(100000)))
                    .build()
            )
        );

        pontosRepository.saveAll(
            List.of(
                Pontos.builder()
                    .pointID(1)
                    .valor(1000)
                    .dataCredito(Date.from(Instant.now().minusSeconds(100000)))
                    .dataExpiracao(Date.from(Instant.now().plusSeconds(100000)))
                    .utilizado(false)
                    .build(),
                Pontos.builder()
                    .pointID(2)
                    .valor(100)
                    .dataCredito(Date.from(Instant.now()))
                    .dataExpiracao(Date.from(Instant.now().plusSeconds(100000)))
                    .utilizado(false)
                    .build()
            )
        );

        produtosRepository.saveAll(
            List.of(
                Produtos.builder()
                    .pdvID(lojaRepository.findById(1).get())
                    .categoriaID(categoriasRepository.findById(1).get())
                    .nome("Produto 1")
                    .descricao("Produto 1")
                    .valor(1000.00)
                    .ativo(true)
                    .build(),
                Produtos.builder()
                    .pdvID(lojaRepository.findById(2).get())
                    .categoriaID(categoriasRepository.findById(2).get())
                    .nome("Produto 2")
                    .descricao("Produto 2")
                    .valor(200.00)
                    .ativo(true)
                    .build()
            )
        );

        usersRepository.saveAll(
            List.of(
                Users.builder()
                    .usersID(1)
                    .nome("Enzo")
                    .sobrenome("Farias")
                    .email("enzo.farias@fiap.com.br")
                    .password("123456")
                    .telefone("(11) 98765-4321")
                    .endereco("Avenida Paulista")
                    .numero("1100")
                    .complemento("4o andar")
                    .ativo(true)
                    .build(),
                Users.builder()
                    .usersID(2)
                    .nome("Beatriz")
                    .sobrenome("Lucas")
                    .email("beatriz.lucas@fiap.com.br")
                    .password("123456")
                    .telefone("(11) 91234-5678")
                    .endereco("Avenida Paulista")
                    .numero("1100")
                    .complemento("3o andar")
                    .ativo(true)
                    .build()
            )
        );

        comprasRepository.saveAll(
            List.of(
                Compras.builder()
                    .compraID(1)
                    .usersID(usersRepository.findById(1).get())
                    .pdvID(lojaRepository.findById(1).get())
                    .valor(3000.00)
                    .dataCompra(Date.from(Instant.now().minusSeconds(100000)))
                    .build(),
                Compras.builder()
                    .compraID(2)
                    .usersID(usersRepository.findById(2).get())
                    .pdvID(lojaRepository.findById(2).get())
                    .valor(2000.00)
                    .dataCompra(Date.from(Instant.now()))
                    .build()
            )
        );
    }
}
