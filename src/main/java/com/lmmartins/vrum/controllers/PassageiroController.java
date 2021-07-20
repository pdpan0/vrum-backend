package com.lmmartins.vrum.controllers;

import com.lmmartins.vrum.models.Passageiro;
import com.lmmartins.vrum.repositories.PassageiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/v1/passageiros")
public class PassageiroController {
    @Autowired
    private PassageiroRepository repository;

    @GetMapping
    public ResponseEntity getPassageiros() {
        List<Passageiro> passageiros = repository.findAll();
        return passageiros.isEmpty() ? noContent().build() : ok(passageiros);
    }

    @PostMapping
    public ResponseEntity criarPassageiro(@RequestBody Passageiro passageiro) {
        return created(null).body(repository.save(passageiro).getId());
    }

    @PutMapping("/{passageiroId}")
    public ResponseEntity atualizarPassageiroById(@PathVariable("passageiroId") Long passageiroId,
                                             @RequestBody Passageiro passageiro) {
        Passageiro passageiroAtualizado = new Passageiro(
                passageiroId,
                passageiro.getNome(),
                passageiro.getDataNasc(),
                passageiro.getCpf(),
                passageiro.getSexo()
        );

        return created(null).body(repository.save(passageiroAtualizado).getId());
    }

    @DeleteMapping("/{passageiroId}")
    public ResponseEntity deletarPassageiroById(@PathVariable("passageiroId") Long passageiroId) {
        repository.deleteById(passageiroId);
        return ok().build();
    }
}
