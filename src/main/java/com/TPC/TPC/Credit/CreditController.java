package com.TPC.TPC.Credit;

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
@RequestMapping("credits")
@CacheConfig(cacheNames = "credits")
@Tag(name = "Créditos", description = "Informações de créditos disponíveis a serem vinculados com diferentes usuários.")
public class CreditController {

    @Autowired
    private CreditService creditService;

    // Buscar todos os créditos
    @GetMapping
    @Cacheable("credits")
    @Operation(
        summary = "Listar créditos",
        description = "Retorna uma página com todos os créditos cadastrados, ordenados pela data de início."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Créditos retornados com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existem créditos cadastrados.", useReturnTypeSchema = false)
    })
    public Page<Credit> listarCreditos(
        @RequestParam(required = false) String credit,
        @PageableDefault(sort = "dataCredito", direction = Direction.ASC  ) Pageable pageable
    ) {
        return creditService.listarCreditos(credit, pageable);
    }

    // Buscar um crédito pelo ID
    @GetMapping("{creditID}")
    @Operation(
        summary = "Listar créditos por ID",
        description = "Retorna um determinado crédito correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados do crédito retornados com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existe crédito com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<Credit> getCreditById(@PathVariable Integer creditID) {
        return creditService.getCreditosById(creditID);
    }

    // Criar um novo crédito
    @PostMapping
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Cadastrar crédito",
        description = "Cadastra um novo crédito a ser vinculado a um determinado usuário com os dados enviados no corpo da requisição."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "201", description = "Crédito cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique as regras para o corpo da requisição", useReturnTypeSchema = false)
    })
    public ResponseEntity<Credit> createCredit(@Valid @RequestBody Credit credit) {
        return creditService.createCreditos(credit);
    }

    // Atualizar um crédito existente
    @PutMapping("{creditID}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Atualizar crédito",
        description = "Atualiza um determinado crédito correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados do crédito atualizados com sucesso."),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique as regras para o corpo da requisição.", useReturnTypeSchema = false),
        @ApiResponse(responseCode = "404", description = "Não existe crédito com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<Credit> updateCredit(@PathVariable Integer creditID, @Valid @RequestBody Credit creditDetails) {
        return creditService.updateCreditos(creditID, creditDetails);
    }

    // Deletar um crédito
    @DeleteMapping("{creditID}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Deletar crédito",
        description = "Deleta um determinado crédito correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados do crédito deletados com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existe crédito com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<?> deleteCredit(@PathVariable Integer creditID) {
        return creditService.deleteCreditos(creditID);
    }
}
