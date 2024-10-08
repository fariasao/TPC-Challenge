package com.TPC.TPC.Campanhas;

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
@RequestMapping("campanhas")
@CacheConfig(cacheNames = "campanhas")
@Tag(name = "Campanhas", description = "Campanhas personalizadas para os usuários.")
public class CampanhasController {

    @Autowired
    private CampanhasService campanhasService;

    // Buscar todas as campanhas
    @GetMapping
    @Cacheable("campanhas")
    @Operation(
        summary = "Listar campanhas",
        description = "Retorna uma página com todas as campanhas cadastradas, ordenadas pelo título."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Campanhas retornadas com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existem campanhas cadastradas.", useReturnTypeSchema = false)
    })
    public Page<Campanhas> listarCampanhas(
        @RequestParam(required = false) String campanha,
        @PageableDefault(sort = "titulo", direction = Direction.ASC  ) Pageable pageable
    ) {
        return campanhasService.listarCampanhas(campanha, pageable);
    }

    // Buscar uma campanha pelo ID
    @GetMapping("{campanhaID}")
    @Operation(
        summary = "Listar campanha por ID",
        description = "Retorna uma determinada campanha correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados da campanha retornados com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existe campanha com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<Campanhas> getCampanhaById(@PathVariable Integer campanhaID) {
        return campanhasService.getCampanhaById(campanhaID);
    }

    // Criar uma nova campanha
    @PostMapping
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Cadastrar campanha",
        description = "Cadastra uma nova campanha, futuramente enviada para usuários selecionados com os dados enviados no corpo da requisição."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "201", description = "Campanha cadastrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique as regras para o corpo da requisição", useReturnTypeSchema = false)
    })
    public ResponseEntity<Campanhas> createCampanha(@Valid @RequestBody Campanhas campanha) {
        return campanhasService.createCampanha(campanha);
    }

    // Atualizar uma campanha existente
    @PutMapping("{campanhaID}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Atualizar campanha",
        description = "Atualiza uma determinada campanha correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados da campanha retornados com sucesso."),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique as regras para o corpo da requisição.", useReturnTypeSchema = false),
        @ApiResponse(responseCode = "404", description = "Não existe campanha com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<Campanhas> updateCampanha(@PathVariable Integer campanhaID, @Valid @RequestBody Campanhas campanhaDetails) {
        return campanhasService.updateCampanha(campanhaID, campanhaDetails);
    }

    // Deletar uma campanha
    @DeleteMapping("{campanhaID}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Deletar campanha",
        description = "Deleta uma determinada campanha correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados da campanha deletados com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existe campanha com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<?> deleteCampanha(@PathVariable Integer campanhaID) {
        return campanhasService.deletePontos(campanhaID);
    }
}
