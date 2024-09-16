package com.TPC.TPC.PontosCompra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping("pontoscompras")
@CacheConfig(cacheNames = "pontoscompras")
@Tag(name = "Pontos-Compras", description = "Relação entre pontos e compras.")
public class PontosCompraController {

    @Autowired
    private PontosCompraService pontosCompraService;

    // Buscar todas as compras de pontos
    @GetMapping
    @Cacheable("pontoscompras")
    @Operation(
        summary = "Listar relações entre pontos e compras",
        description = "Retorna uma página com todas as relações entre pontos e compras cadastradas, ordenadas pelo ID."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Relações entre pontos e compras retornados com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existem relações entre pontos e compras cadastradas.", useReturnTypeSchema = false)
    })
    public Page<PontosCompra> listarPontosCompra(
        @RequestParam(required = false) String pontosCompra,
        @PageableDefault(sort = "pontosCompraID", direction = Direction.ASC) Pageable pageable
    ) {
        return pontosCompraService.listarPontosCompra(pontosCompra, pageable);
    }

    // Buscar uma compra de pontos pelo ID do pedido
    @GetMapping("{pontosCompraID}")
    @Operation(
        summary = "Listar relação entre ponto e compra por ID",
        description = "Retorna uma determinada relação entre ponto e compra correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados da relação entre ponto e compra retornados com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existe relação entre ponto e compra com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<PontosCompra> getCompraPontosById(@PathVariable Integer pontosCompraID) {
        return pontosCompraService.getCompraPontosById(pontosCompraID);
    }

    // Criar uma nova compra de pontos
    @PostMapping
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Cadastrar relação entre ponto e compra",
        description = "Cadastra uma nova relação entre ponto e compra com os dados enviados no corpo da requisição."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "201", description = "Relação entre ponto e compra cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique as regras para o corpo da requisição", useReturnTypeSchema = false)
    })
    public ResponseEntity<PontosCompra> createCompraPontos(@Valid @RequestBody PontosCompra pontosCompra) {
        return pontosCompraService.createCompraPontos(pontosCompra);
    }

    // Atualizar uma compra de pontos existente
    @PutMapping("{pontosCompraID}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Atualizar relação entre ponto e compra",
        description = "Atualiza uma determinada relação entre ponto e compra correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados da relação entre ponto e compra atualizados com sucesso."),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique as regras para o corpo da requisição.", useReturnTypeSchema = false),
        @ApiResponse(responseCode = "404", description = "Não existe relação entre ponto e compra com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<PontosCompra> updateCompraPontos(@PathVariable Integer pontosCompraID, @Valid @RequestBody PontosCompra pontosCompraDetails) {
        return pontosCompraService.updateCompraPontos(pontosCompraID, pontosCompraDetails);
    }

    // Deletar uma compra de pontos
    @DeleteMapping("{pontosCompraID}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Deletar relação entre ponto e compra",
        description = "Deleta uma determinada relação entre ponto e compra correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados da relação entre ponto e compra deletados com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existe relação entre ponto e compra com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<?> deleteCompraPontos(@PathVariable Integer pontosCompraID) {
        return pontosCompraService.deleteCompraPontos(pontosCompraID);
    }
}
