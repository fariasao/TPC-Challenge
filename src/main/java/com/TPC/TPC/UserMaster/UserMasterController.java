package com.TPC.TPC.UserMaster;

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
@RequestMapping("usermasters")
@CacheConfig(cacheNames = "usermasters")
@Tag(name = "Usuário Master", description = "Usuários master cadastrados.")
public class UserMasterController {

    @Autowired
    private UserMasterService userMasterService;

    // Buscar todos os usuários mestres
    @GetMapping
    @Cacheable("usermasters")
    @Operation(
        summary = "Listar usuários master",
        description = "Retorna uma página com todos os usuários master cadastrados, ordenados pelo nome."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Usuários master retornados com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existem usuários master cadastrados.", useReturnTypeSchema = false)
    })
    public Page<UserMaster> listarUserMasters(
        @RequestParam(required = false) String userMaster,
        @PageableDefault(sort = "nome", direction = Direction.ASC) Pageable pageable
    ) {
        return userMasterService.listarUserMasters(userMaster, pageable);
    }

    // Buscar um usuário mestre pelo ID
    @GetMapping("{masterID}")
    @Operation(
        summary = "Listar usuário master por ID",
        description = "Retorna um determinado usuário master correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados do usuário master retornados com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existe usuário master com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<UserMaster> getUserMasterById(@PathVariable Integer masterID) {
        return userMasterService.getUserMasterById(masterID);
    }

    // Criar um novo usuário mestre
    @PostMapping
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Cadastrar usuário master",
        description = "Cadastra um novo usuário master com os dados enviados no corpo da requisição."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "201", description = "Usuário master cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique as regras para o corpo da requisição", useReturnTypeSchema = false)
    })
    public ResponseEntity<UserMaster> createUserMaster(@Valid @RequestBody UserMaster userMaster) {
        return userMasterService.createUserMaster(userMaster);
    }

    // Atualizar um usuário mestre existente
    @PutMapping("{masterID}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Atualizar usuário master",
        description = "Atualiza um determinado usuário master correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados do usuário master atualizados com sucesso."),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique as regras para o corpo da requisição.", useReturnTypeSchema = false),
        @ApiResponse(responseCode = "404", description = "Não existe usuário master com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<UserMaster> updateUserMaster(@PathVariable Integer masterID, @Valid @RequestBody UserMaster userMasterDetails) {
        return userMasterService.updateUserMaster(masterID, userMasterDetails);
    }

    // Deletar um usuário mestre
    @DeleteMapping("{masterID}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Deletar usuário master",
        description = "Deleta um determinado usuário master correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados do usuário master deletados com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existe usuário master com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<?> deleteUserMaster(@PathVariable Integer masterID) {
        return userMasterService.deleteUserMaster(masterID);
    }
}
