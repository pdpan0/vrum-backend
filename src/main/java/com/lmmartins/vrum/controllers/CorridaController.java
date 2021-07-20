package com.lmmartins.vrum.controllers;

import com.lmmartins.vrum.models.Corrida;
import com.lmmartins.vrum.repositories.CorridaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/v1/corridas")
public class CorridaController {
    @Autowired
    private CorridaRepository repository;

    @GetMapping
    public ResponseEntity getCorridas() {
        List<Corrida> corridas = repository.findAll();
        return corridas.isEmpty() ? noContent().build() : ok(corridas);
    }

    @PostMapping
    public ResponseEntity criarCorrida(@RequestBody Corrida corrida) {
        return created(null).body(repository.save(corrida).getId());
    }

    @PutMapping("/{corridaId}")
    public ResponseEntity atualizarCorridaById(@PathVariable("corridaId") Long corridaId,
                                                 @RequestBody Corrida corrida) {

        //TODO: Validar o motorista e passageiro.

        Corrida corridaAtualizada = new Corrida(
                corridaId,
                corrida.getMotorista(),
                corrida.getPassageiro(),
                corrida.getPrecoTotal()
        );

        return created(null).body(repository.save(corridaAtualizada).getId());
    }

    @DeleteMapping("/{corridaId}")
    public ResponseEntity deletarCorridaById(@PathVariable("corridaId") Long corridaId) {
        repository.deleteById(corridaId);
        return ok().build();
    }
}
