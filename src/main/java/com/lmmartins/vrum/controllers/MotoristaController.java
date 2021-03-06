package com.lmmartins.vrum.controllers;

import com.lmmartins.vrum.dto.CorpoRespostaDTO;
import com.lmmartins.vrum.dto.ExisteDTO;
import com.lmmartins.vrum.dto.MotoristaDTO;
import com.lmmartins.vrum.exceptions.ValidacaoException;
import com.lmmartins.vrum.models.Motorista;
import com.lmmartins.vrum.enums.MotoristaStatus;
import com.lmmartins.vrum.services.MotoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/v1/motoristas")
public class MotoristaController {

    @Autowired
    private MotoristaService service;

    @GetMapping
    public ResponseEntity getMotoristas() {
        //TODO: Sort, Paginação, Filtros
        List<Motorista> motoristas = service.getMotoristas();
        return motoristas.isEmpty() ? noContent().build() : ok(motoristas);
    }

    @GetMapping("/{motoristaId}")
    public ResponseEntity getMotoristasPorId(@PathVariable("motoristaId") Long motoristaId) {
        Optional<Motorista> motorista = service.getMotoristasPorId(motoristaId);
        return motorista.isEmpty() ? noContent().build() : ok(motorista);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity getMotoristasPorCPF(@PathVariable("cpf") String cpf) {
        return ok(new ExisteDTO(service.existMotoristaPorCPF(cpf)));
    }

    @GetMapping("/count")
    public ResponseEntity getMotoristaCount() {
        return ok(service.getTotalMotoristas());
    }

    @PostMapping
    public ResponseEntity criarMotorista(@RequestBody MotoristaDTO motorista) {
        try {
            return created(null).body(service.criarMotorista(motorista));
        } catch (ValidacaoException e) {
            return badRequest().body(
                    new CorpoRespostaDTO(400,
                            "BadRequestException",
                            e.getMessage(),
                            null));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return internalServerError().body(new CorpoRespostaDTO(500,
                    "InternalServerException",
                    "Não foi possível realizar essa operação.",
                    null));
        }
    }

    @PutMapping("/{motoristaId}")
    public ResponseEntity atualizarMotorista(@PathVariable("motoristaId") Long motoristaId,
                                             @RequestBody MotoristaDTO motorista) {
        try {
            return ok().body(service.atualizarMotorista(motoristaId, motorista));
        } catch (ValidacaoException e) {
            return badRequest().body(
                    new CorpoRespostaDTO(400,
                            "BadRequestException",
                            e.getMessage(),
                            motoristaId.toString()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return internalServerError().body(new CorpoRespostaDTO(500,
                    "InternalServerException",
                    "Não foi possível realizar essa operação.",
                    null));
        }
    }

    @PutMapping("/{motoristaId}/ativar")
    public ResponseEntity ativarMotorista(@PathVariable("motoristaId") Long motoristaId) {
        try {
            service.atualizarStatusDoMotorista(motoristaId, MotoristaStatus.ATIVO);
            return ok().build();
        } catch (ValidacaoException e) {
            return badRequest().body(
                    new CorpoRespostaDTO(400,
                            "BadRequestException",
                            e.getMessage(),
                            motoristaId.toString()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return internalServerError().body(new CorpoRespostaDTO(500,
                    "InternalServerException",
                    "Não foi possível realizar essa operação.",
                    null));
        }
    }

    @PutMapping("/{motoristaId}/inativar")
    public ResponseEntity inativarMotorista(@PathVariable("motoristaId") Long motoristaId) {
        try {
            service.atualizarStatusDoMotorista(motoristaId, MotoristaStatus.INATIVO);
            return ok().build();
        } catch (ValidacaoException e) {
            return badRequest().body(
                    new CorpoRespostaDTO(400,
                            "BadRequestException",
                            e.getMessage(),
                            motoristaId.toString()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return internalServerError().body(new CorpoRespostaDTO(500,
                    "InternalServerException",
                    "Não foi possível realizar essa operação.",
                    null));
        }
    }

    @DeleteMapping("/{motoristaId}")
    public ResponseEntity deletarMotoristaPorId(@PathVariable("motoristaId") Long motoristaId) {
        try {
            service.deletarMotoristaPorId(motoristaId);
            return noContent().build();
        } catch (ValidacaoException e) {
            return badRequest().body(
                    new CorpoRespostaDTO(400,
                            "BadRequestException",
                            e.getMessage(),
                            motoristaId.toString()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return internalServerError().body(new CorpoRespostaDTO(500,
                    "InternalServerException",
                    "Não foi possível realizar essa operação.",
                    null));
        }
    }
}
