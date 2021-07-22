package com.lmmartins.vrum.controllers;

import com.lmmartins.vrum.dto.PassageiroDTO;
import com.lmmartins.vrum.exceptions.ValidacaoException;
import com.lmmartins.vrum.models.Passageiro;
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

    @GetMapping("/count")
    public ResponseEntity getPassageirosCount() {
        return ok(service.getTotalPassageiros());
    }

    @PostMapping
    public ResponseEntity criarPassageiro(@RequestBody PassageiroDTO passageiroDto) {
        try {
            return created(null).body(service.criarPassageiro(passageiroDto));
        } catch (ValidacaoException e) {
            return badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return internalServerError().build();
        }
    }

    @PutMapping("/{passageiroId}")
    public ResponseEntity atualizarPassageiro(@PathVariable("passageiroId") Long passageiroId,
                                             @RequestBody PassageiroDTO passageiroDto) {
        try {
            return ok().body(service.atualizarPassageiro(passageiroId, passageiroDto));
        } catch (ValidacaoException e) {
            return badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return internalServerError().build();
        }
    }

    @DeleteMapping("/{passageiroId}")
    public ResponseEntity deletarPassageiro(@PathVariable("passageiroId") Long passageiroId) {
        try {
            service.deletarPassageiroPorId(passageiroId);
            return noContent().build();
        } catch (ValidacaoException e) {
            return badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return internalServerError().build();
        }
    }
}
