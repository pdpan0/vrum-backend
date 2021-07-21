package com.lmmartins.vrum.services;

import com.lmmartins.vrum.enums.MotoristaStatus;
import com.lmmartins.vrum.models.Motorista;
import com.lmmartins.vrum.repositories.MotoristaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class MotoristaService {

    @Autowired
    private MotoristaRepository repository;

    //Metódos de Consultas
    //TODO: Sort, Paginação, Filtros
    public List<Motorista> getMotoristas() {
        return repository.findAll();
    }

    public Optional<Motorista> getMotoristasPorId(Long motoristaId) {
        return repository.findById(motoristaId);
    }

    //Metódos de Criação
    public Long criarMotorista(Motorista motorista) {
        return repository.save(motorista).getId();
    }

    //Metódos de Atualização
    public Long atualizarMotorista(Long motoristaId,
                                   Motorista motorista) {
        motorista.setId(motoristaId);
        return repository.save(motorista).getId();
    }

    public Integer atualizarStatusDoMotorista(Long motoristaId, MotoristaStatus status) {
        return repository.setMotoristaStatus(motoristaId, status.motoristaStatus);
    }

    //Metódos de Deleção.
    public void deletarMotoristaPorId(@PathVariable("motoristaId") Long motoristaId) {
        repository.deleteById(motoristaId);
    }
}
