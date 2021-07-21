package com.lmmartins.vrum.services;

import com.lmmartins.vrum.dto.CorridaDTO;
import com.lmmartins.vrum.models.Corrida;
import com.lmmartins.vrum.repositories.CorridaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;


@Service
public class CorridaService {
    @Autowired
    private CorridaRepository repository;

    @Autowired
    private ModelMapper mapper;

    //TODO: Avaliar se necessário.
    public List<Corrida> getCorridasPorPassageiroEMotorista(Long passageiroId, Long motoristaId) {
        return repository.findByPassageiroIdOrMotoristaId(passageiroId, motoristaId);
    }

    public List<Corrida> getCorridasPorPassageiro(Long passageiroId) {
        return repository.findByPassageiroId(passageiroId);
    }

    public List<Corrida> getCorridasPorMotorista(Long motoristaId) {
        return repository.findByMotoristaId(motoristaId);
    }

    public Long criarCorrida(CorridaDTO corridaDto) {

        Corrida corrida = mapper.map(corridaDto, Corrida.class);
        return repository.save(corrida).getId();
    }

    public Long atualizarCorridaPorId(Long corridaId, CorridaDTO corridaDto) {
        Corrida corrida = mapper.map(corridaDto, Corrida.class);
        corrida.setId(corridaId);
        return repository.save(corrida).getId();
    }

    public void deletarCorridaPorId(Long corridaId) {
        repository.deleteById(corridaId);
    }

    private Boolean validarCorrida(CorridaDTO corrida) {
        //TODO: Implementar validação.
        return false;
    }
}
