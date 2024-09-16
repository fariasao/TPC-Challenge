package com.TPC.TPC.Compras;

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
@RequestMapping("compras")
@CacheConfig(cacheNames = "compras")
@Tag(name = "Compras", description = "Informações de compras feitas por usuários e nas lojas específicas.")
public class ComprasController {

    @Autowired
    private ComprasService comprasService;

    // Buscar todas as compras
    @GetMapping
    @Cacheable("compras")
    @Operation(
        summary = "Listar compras",
        description = "Retorna uma página com todas as compras cadastradas, ordenadas pela data de compra."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Compras retornadas com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existe compras cadastradas.", useReturnTypeSchema = false)
    })
    public Page<Compras> listarCompras(
        @RequestParam(required = false) String compras,
        @PageableDefault(sort = "dataCompra", direction = Direction.DESC  ) Pageable pageable
    ) {
        return comprasService.listarCompras(compras, pageable);
    }

    // Buscar uma compra pelo ID
    @GetMapping("{compraID}")
    @Operation(
        summary = "Listar compra por ID",
        description = "Retorna uma determinada compra correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados da compra retornado com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existe compra com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<Compras> getCompraById(@PathVariable Integer compraID) {
        return comprasService.getCompraById(compraID);
    }

    // Criar uma nova compra
    @PostMapping
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Cadastrar compra",
        description = "Cadastra uma nova compra com os dados do corpo da requisição."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "201", description = "Compra cadastrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique as regras para o corpo da requisição", useReturnTypeSchema = false)
    })
    public ResponseEntity<Compras> createCompra(@Valid @RequestBody Compras compra) {
        return comprasService.createCompra(compra);
    }

    // Atualizar uma compra existente
    @PutMapping("{compraID}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Atualizar compra",
        description = "Atualiza uma determinada compra correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados da compra atualizados com sucesso."),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique as regras para o corpo da requisição.", useReturnTypeSchema = false),
        @ApiResponse(responseCode = "404", description = "Não existe compra com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<Compras> updateCompra(@PathVariable Integer compraID, @Valid @RequestBody Compras compraDetails) {
        return comprasService.updateCompra(compraID, compraDetails);
    }

    // Deletar uma compra
    @DeleteMapping("{compraID}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Deletar compra",
        description = "Deleta uma determinada compra correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados da compra deletados com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existe compra com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<?> deleteCompra(@PathVariable Integer compraID) {
        return comprasService.deleteCompra(compraID);
    }
}
