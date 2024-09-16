package com.TPC.TPC.Categorias;

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
@RequestMapping("categorias")
@CacheConfig(cacheNames = "categorias")
@Tag(name = "Categorias", description = "Categorias dos produtos cadastrados.")
public class CategoriasController {

    @Autowired
    private CategoriasService categoriasService;

    // Buscar todas as categorias
    @GetMapping
    @Cacheable("categorias")
    @Operation(
        summary = "Listar categorias",
        description = "Retorna uma página com todas as categorias cadastradas, ordenadas pelo nome."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Categorias retornadas com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existem categorias cadastradas.", useReturnTypeSchema = false)
    })
    public Page<Categorias> listarCategorias(
        @RequestParam(required = false) String categorias,
        @PageableDefault(sort = "nome", direction = Direction.ASC  ) Pageable pageable
    ) {
        return categoriasService.listarCategorias(categorias, pageable);
    }

    // Buscar uma categoria pelo ID
    @GetMapping("{categoriaID}")
    @Operation(
        summary = "Listar categoria por ID",
        description = "Retorna uma determinada categoria correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados da categoria retornado com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existe categoria com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<Categorias> getCategoriaById(@PathVariable Integer categoriaID) {
        return categoriasService.getCategoriaById(categoriaID);
    }

    // Criar uma nova categoria
    @PostMapping
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Cadastrar Categoria",
        description = "Cadastra uma nova categoria a serem utilizadas para diferenciar os produtos com os dados enviados no corpo da requisição."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "201", description = "Categoria cadastrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique as regras para o corpo da requisição", useReturnTypeSchema = false)
    })
    public ResponseEntity<Categorias> createCategoria(@Valid @RequestBody Categorias categoria) {
        return categoriasService.createCategoria(categoria);
    }

    // Atualizar uma categoria existente
    @PutMapping("{categoriaID}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Atualizar categoria",
        description = "Atualiza uma determinada categoria correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados da categoria atualizados com sucesso."),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique as regras para o corpo da requisição.", useReturnTypeSchema = false),
        @ApiResponse(responseCode = "404", description = "Não existe categoria com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<Categorias> updateCategoria(@PathVariable Integer categoriaID, @Valid @RequestBody Categorias categoriaDetails) {
        return categoriasService.updateCategorias(categoriaID, categoriaDetails);
    }

    // Deletar uma categoria
    @DeleteMapping("{categoriaID}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Deletar categoria",
        description = "Deleta uma determinada categoria correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados da categoria deletados com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existe categoria com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<?> deleteCategoria(@PathVariable Integer categoriaID) {
        return categoriasService.deleteCategoria(categoriaID);
    }
}
