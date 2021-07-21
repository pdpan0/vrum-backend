package com.lmmartins.vrum.services;

import com.lmmartins.vrum.models.Passageiro;
import com.lmmartins.vrum.repositories.PassageiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassageiroService {
    @Autowired
    private PassageiroRepository repository;

    //Metódos de Consultas
    //TODO: Sort, Paginação, Filtros
    public List<Passageiro> getPassageiros() {
        return repository.findAll();
    }

    public Optional<Passageiro> getPassageiroPorId(Long passageiroId) {
        return repository.findById(passageiroId);
    }

    //Metódos de Criação
    public Long criarPassageiro(Passageiro passageiro) {
        return repository.save(passageiro).getId();
    }

    //Metódos de Atualização
    public Long atualizarPassageiro(Long passageiroId,
                                   Passageiro passageiro) {
        passageiro.setId(passageiroId);
        return repository.save(passageiro).getId();
    }

    //Metódos de Deleção.
    public void deletarPassageiroPorId(Long passageiroId) {
        repository.deleteById(passageiroId);
    }
}
