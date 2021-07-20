package com.lmmartins.vrum.controllers;

import com.lmmartins.vrum.models.Motorista;
import com.lmmartins.vrum.repositories.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/v1/motoristas")
public class MotoristaController {
    @Autowired
    private MotoristaRepository repository;

    @GetMapping
    public ResponseEntity getMotoristas() {
        //TODO: Sort, Paginação, Filtros
        List<Motorista> motoristas = repository.findAll();
        return motoristas.isEmpty() ? noContent().build() : ok(motoristas);
    }

    @PostMapping
    public ResponseEntity criarMotorista(@RequestBody Motorista motorista) {
        return created(null).body(repository.save(motorista).getId());
    }

    @PutMapping("/{motoristaId}")
    public ResponseEntity atualizarMotoristaById(@PathVariable("motoristaId") Long motoristaId,
                                             @RequestBody Motorista motorista) {
        Motorista motoristaAtualizado = new Motorista(
                motoristaId,
                motorista.getNome(),
                motorista.getDataNasc(),
                motorista.getCpf(),
                motorista.getSexo(),
                motorista.getModeloCarro(),
                motorista.getStatus()
                );

        return created(null).body(repository.save(motoristaAtualizado).getId());
    }

    @PutMapping("/{motoristaId}/ativar")
    public ResponseEntity inativarMotoristaById(@PathVariable("motoristaId") Long motoristaId) {
        return unprocessableEntity().build();
    }

    @DeleteMapping("/{motoristaId}")
    public ResponseEntity deletarMotoristaById(@PathVariable("motoristaId") Long motoristaId) {
        repository.deleteById(motoristaId);
        return ok().build();
    }
}
