package com.TPC.TPC.CreditCompras;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

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

import jakarta.validation.Valid;

@RestController
@RequestMapping("creditcompras")
@CacheConfig(cacheNames = "creditcompras")
@Tag(name = "Crédito-Compras", description = "Relação entre compras e créditos.")
public class CreditComprasController {

    @Autowired
    private CreditComprasService creditComprasService;

    // Buscar todas as compras de pontos
    @GetMapping
    @Cacheable("creditcompras")
    @Operation(
        summary = "Listar relações entre compras e créditos",
        description = "Retorna uma página com todas as relações entre compras e créditos cadastradas, ordenadas pelo ID."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Relações entre compras e créditos retornados com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existem relações entre compras e créditos cadastradas.", useReturnTypeSchema = false)
    })
    public Page<CreditCompras> listarCreditCompras(
        @RequestParam(required = false) String creditCompras,
        @PageableDefault(sort = "creditCompraID", direction = Direction.ASC  ) Pageable pageable
    ) {
        return creditComprasService.listarCreditCompras(creditCompras, pageable);
    }

    // Buscar uma compra de pontos pelo ID do pedido
    @GetMapping("{creditCompraID}")
    @Operation(
        summary = "Listar relação entre compra e crédito por ID",
        description = "Retorna uma determinada relação entre compra e crédito correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados da relação entre compra e crédito retornados com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existe relação entre compra e crédito com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<CreditCompras> getCompraPontosById(@PathVariable Integer creditCompraID) {
        return creditComprasService.getCreditComprasById(creditCompraID);
    }

    // Criar uma nova compra de pontos
    @PostMapping
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Cadastrar relação entre compra e crédito",
        description = "Cadastra uma nova relação entre compra e crédito com os dados enviados no corpo da requisição."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "201", description = "Relação entre compra e crédito cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique as regras para o corpo da requisição", useReturnTypeSchema = false)
    })
    public ResponseEntity<CreditCompras> createCompraPontos(@Valid @RequestBody CreditCompras compraPontos) {
        return creditComprasService.createCreditCompras(compraPontos);
    }

    // Atualizar uma compra de pontos existente
    @PutMapping("{creditCompraID}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Atualizar relação entre compra e crédito",
        description = "Atualiza uma determinada relação entre compra e crédito correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados da relação entre compra e crédito atualizados com sucesso."),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique as regras para o corpo da requisição.", useReturnTypeSchema = false),
        @ApiResponse(responseCode = "404", description = "Não existe relação entre compra e crédito com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<CreditCompras> updateCompraPontos(@PathVariable Integer creditCompraID, @Valid @RequestBody CreditCompras compraPontosDetails) {
        return creditComprasService.updateCreditCompras(creditCompraID, compraPontosDetails);
    }

    // Deletar uma compra de pontos
    @DeleteMapping("{creditCompraID}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Deletar relação entre compra e crédito",
        description = "Deleta uma determinada relação entre compra e crédito correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados da relação entre compra e crédito deletados com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existe relação entre compra e crédito com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<?> deleteCompraPontos(@PathVariable Integer creditCompraID) {
        return creditComprasService.deleteCreditCompras(creditCompraID);
    }
}
