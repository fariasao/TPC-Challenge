package com.TPC.TPC.UserPDV;

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
@RequestMapping("userpdv")
@CacheConfig(cacheNames = "userpdv")
@Tag(name = "Usuário cadastrado em loja", description = "Cadastro de usuários em lojas.")
public class UserPDVController {

    @Autowired
    private UserPDVService userPDVService;

    // Buscar todos os usuários PDV
    @GetMapping
    @Cacheable("userpdv")
    @Operation(
        summary = "Listar usuários cadastrados em lojas",
        description = "Retorna uma página com todos os usuários cadastrados em lojas, ordenados pelo nome."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Usuários cadastrados em lojas retornados com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existem usuários cadastrados em lojas.", useReturnTypeSchema = false)
    })
    public Page<UserPDV> listarUserPDV(
        @RequestParam(required = false) String userPDV,
        @PageableDefault(sort = "nome", direction = Direction.ASC) Pageable pageable
    ) {
        return userPDVService.listarUserPDV(userPDV, pageable);
    }

    // Buscar um usuário PDV pelo ID
    @GetMapping("{userPdvID}")
    @Operation(
        summary = "Listar usuário cadastrado em loja por ID",
        description = "Retorna um determinado usuário cadastrado em loja correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados do usuário cadastrado em loja retornados com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existe usuário cadastrado em loja com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<UserPDV> getUserPDVById(@PathVariable Integer userPdvID) {
        return userPDVService.getUserPDVById(userPdvID);
    }

    // Criar um novo usuário PDV
    @PostMapping
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Cadastrar usuário em loja",
        description = "Cadastra um novo usuário em loja a ser vinculado à determinada loja com os dados enviados no corpo da requisição."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "201", description = "Usuário cadastrado em loja com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique as regras para o corpo da requisição", useReturnTypeSchema = false)
    })
    public ResponseEntity<UserPDV> createUserPDV(@Valid @RequestBody UserPDV userPDV) {
        return userPDVService.createUserPDV(userPDV);
    }

    // Atualizar um usuário PDV existente
    @PutMapping("{userPdvID}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Atualizar usuário cadastrado em loja",
        description = "Atualiza um determinado usuário cadastrado em loja correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados do usuário cadastrado em loja atualizados com sucesso."),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique as regras para o corpo da requisição.", useReturnTypeSchema = false),
        @ApiResponse(responseCode = "404", description = "Não existe usuário cadastrado em loja com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<UserPDV> updateUserPDV(@PathVariable Integer userPdvID, @Valid @RequestBody UserPDV userPDVDetails) {
        return userPDVService.updateUserPDV(userPdvID, userPDVDetails);
    }

    // Deletar um usuário PDV
    @DeleteMapping("{userPdvID}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Deletar usuário cadastrado em loja",
        description = "Deleta um determinado usuário cadastrado em loja correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados do usuário cadastrado em loja deletados com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existe usuário cadastrado em loja com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<?> deleteUserPDV(@PathVariable Integer userPdvID) {
        return userPDVService.deleteUserPDV(userPdvID);
    }
}
