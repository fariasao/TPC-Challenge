package com.TPC.TPC.Notificacoes;

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
@RequestMapping("notificacoes")
@CacheConfig(cacheNames = "notificacoes")
@Tag(name = "Notificações", description = "Notificações personalizadas para determinadas campanhas.")
public class NotificacoesController {

    @Autowired
    private NotificacoesService notificacoesService;

    // Buscar todas as notificações
    @GetMapping
    @Cacheable("notificacoes")
    @Operation(
        summary = "Listar notificações",
        description = "Retorna uma página com todas as notificações cadastradas, ordenadas pela data de envio."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Notificações retornadas com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existem notificações cadastradas.", useReturnTypeSchema = false)
    })
    public Page<Notificacoes> listarNotificacoes(
        @RequestParam(required = false) String notificacoes,
        @PageableDefault(sort = "dataEnvio", direction = Direction.ASC  ) Pageable pageable
    ) {
        return notificacoesService.listarNotificacoes(notificacoes, pageable);
    }

    // Buscar uma notificação pelo ID
    @GetMapping("{notificacoesID}")
    @Operation(
        summary = "Listar notificação por ID",
        description = "Retorna uma determinada notificação correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados da notificação retornados com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existe notificação com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<Notificacoes> getNotificacaoById(@PathVariable Integer notificacoesID) {
        return notificacoesService.getNotificacoesById(notificacoesID);
    }

    // Criar uma nova notificação
    @PostMapping
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Cadastrar notificação",
        description = "Cadastra uma nova notificação, utilizada nas campanhas personalizadas com os dados do corpo da requisição."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "201", description = "Notificação cadastrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique as regras para o corpo da requisição", useReturnTypeSchema = false)
    })
    public ResponseEntity<Notificacoes> createNotificacao(@Valid @RequestBody Notificacoes notificacao) {
        return notificacoesService.createNotificacoes(notificacao);
    }

    // Atualizar uma notificação existente
    @PutMapping("{notificacoesID}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Atualizar notificação",
        description = "Atualiza uma determinada notificação correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados da notificação retornados com sucesso."),
        @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique as regras para o corpo da requisição.", useReturnTypeSchema = false),
        @ApiResponse(responseCode = "404", description = "Não existe notificação com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<Notificacoes> updateNotificacao(@PathVariable Integer notificacoesID, @Valid @RequestBody Notificacoes notificacaoDetails) {
        return notificacoesService.updateNotificacoes(notificacoesID, notificacaoDetails);
    }

    // Deletar uma notificação
    @DeleteMapping("{notificacoesID}")
    @CacheEvict(allEntries = true)
    @Operation(
        summary = "Deletar notificação",
        description = "Deleta uma determinada notificação correspondente com o ID selecionado."
    )
    @ApiResponses({ 
        @ApiResponse(responseCode = "200", description = "Dados da notificação deletados com sucesso."),
        @ApiResponse(responseCode = "404", description = "Não existe notificação com o id informado.", useReturnTypeSchema = false)
    })
    public ResponseEntity<?> deleteNotificacao(@PathVariable Integer notificacoesID) {
        return notificacoesService.deleteNotificacoes(notificacoesID);
    }
}
