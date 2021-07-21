package com.lmmartins.vrum.controllers;

import com.lmmartins.vrum.dto.CorridaDTO;
import com.lmmartins.vrum.models.Corrida;
import com.lmmartins.vrum.services.CorridaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/v1/corridas")
public class CorridaController {

    @Autowired
    private CorridaService service;

//    @GetMapping
//    public ResponseEntity getCorridasByParams(@RequestParam(value = "passageiroId",required = false) Long passageiroId,
//                                              @RequestParam(value = "motoristaId",required = false) Long motoristaId) {
//        List<Corrida> corridas = service.getCorridasPorPassageiroEMotorista(passageiroId, motoristaId);
//        return corridas.isEmpty() ? noContent().build() : ok(corridas);
//    }

    @GetMapping("/passageiros/{passageiroId}")
    public ResponseEntity getCorridasPorPassageiro(@PathVariable("passageiroId") Long passageiroId) {
        List<Corrida> corridas = service.getCorridasPorPassageiro(passageiroId);
        return corridas.isEmpty() ? noContent().build() : ok(corridas);
    }

    @GetMapping("/motoristas/{motoristaId}")
    public ResponseEntity getCorridasPorMotorista(@PathVariable("motoristaId") Long motoristaId) {
        List<Corrida> corridas = service.getCorridasPorMotorista(motoristaId);
        return corridas.isEmpty() ? noContent().build() : ok(corridas);
    }

    @PostMapping
    public ResponseEntity criarCorrida(@RequestBody CorridaDTO corrida) {
        return created(null).body(service.criarCorrida(corrida));
    }

    @PutMapping("/{corridaId}")
    public ResponseEntity atualizarCorridaPorId(@PathVariable("corridaId") Long corridaId,
                                                 @RequestBody CorridaDTO corrida) {
        //TODO: Validar o motorista e passageiro.
        return created(null).body(service.atualizarCorridaPorId(corridaId, corrida));
    }

    @DeleteMapping("/{corridaId}")
    public ResponseEntity deletarCorridaPorId(@PathVariable("corridaId") Long corridaId) {
        service.deletarCorridaPorId(corridaId);
        return ok().build();
    }
}
