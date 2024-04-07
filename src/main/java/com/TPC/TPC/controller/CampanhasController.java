package com.TPC.TPC.controller;

import com.TPC.TPC.model.Campanhas;
import com.TPC.TPC.repository.CampanhasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/campanhas")
public class CampanhasController {

    @Autowired
    private CampanhasRepository campanhasRepository;

    // Buscar todas as campanhas
    @GetMapping
    public List<Campanhas> getAllCampanhas() {
        return campanhasRepository.findAll();
    }

    // Buscar uma campanha pelo ID
    @GetMapping("/{campanhaId}")
    public ResponseEntity<Campanhas> getCampanhaById(@PathVariable Integer campanhaId) {
        return campanhasRepository.findById(campanhaId)
                .map(campanha -> ResponseEntity.ok(campanha))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Criar uma nova campanha
    @PostMapping
    public ResponseEntity<Campanhas> createCampanha(@Valid @RequestBody Campanhas campanha) {
        Campanhas savedCampanha = campanhasRepository.save(campanha);
        return new ResponseEntity<>(savedCampanha, HttpStatus.CREATED);
    }

    // Atualizar uma campanha existente
    @PutMapping("/{campanhaId}")
    public ResponseEntity<Campanhas> updateCampanha(@PathVariable Integer campanhaId, @Valid @RequestBody Campanhas campanhaDetails) {
        return campanhasRepository.findById(campanhaId)
                .map(campanha -> {
                    campanha.setMasterId(campanhaDetails.getMasterId());
                    campanha.setClusterId(campanhaDetails.getClusterId());
                    campanha.setTitulo(campanhaDetails.getTitulo());
                    campanha.setConteudo(campanhaDetails.getConteudo());
                    campanha.setDescricao(campanhaDetails.getDescricao());
                    campanha.setCanalTipo(campanhaDetails.getCanalTipo());
                    final Campanhas updatedCampanha = campanhasRepository.save(campanha);
                    return ResponseEntity.ok(updatedCampanha);
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar uma campanha
    @DeleteMapping("/{campanhaId}")
    public ResponseEntity<?> deleteCampanha(@PathVariable Integer campanhaId) {
        return campanhasRepository.findById(campanhaId)
                .map(campanha -> {
                    campanhasRepository.delete(campanha);
                    return ResponseEntity.ok().build();
                }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
