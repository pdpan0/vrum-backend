package com.lmmartins.vrum.controllers;

import com.lmmartins.vrum.models.Passageiro;
import com.lmmartins.vrum.repositories.PassageiroRepository;
import com.lmmartins.vrum.services.PassageiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/v1/passageiros")
public class PassageiroController {
    @Autowired
    private PassageiroService service;

    @GetMapping
    public ResponseEntity getPassageiros() {
        List<Passageiro> passageiros = service.getPassageiros();
        return passageiros.isEmpty() ? noContent().build() : ok(passageiros);
    }

    @GetMapping("/{passageiroId}")
    public ResponseEntity getPassageirosPorId(@PathVariable("passageiroId") Long passageiroId) {
        Optional<Passageiro> passageiros = service.getPassageiroPorId(passageiroId);
        return passageiros.isEmpty() ? noContent().build() : ok(passageiros);
    }

    @PostMapping
    public ResponseEntity criarPassageiro(@RequestBody Passageiro passageiro) {
        return created(null).body(service.criarPassageiro(passageiro));
    }

    @PutMapping("/{passageiroId}")
    public ResponseEntity atualizarPassageiro(@PathVariable("passageiroId") Long passageiroId,
                                             @RequestBody Passageiro passageiro) {
        return ok(service.atualizarPassageiro(passageiroId,passageiro));
    }

    @DeleteMapping("/{passageiroId}")
    public ResponseEntity deletarPassageiro(@PathVariable("passageiroId") Long passageiroId) {
        service.deletarPassageiroPorId(passageiroId);
        return noContent().build();
    }
}
