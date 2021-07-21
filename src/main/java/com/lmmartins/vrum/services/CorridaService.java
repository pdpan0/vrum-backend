package com.lmmartins.vrum.services;

import com.lmmartins.vrum.dto.CorridaDTO;
import com.lmmartins.vrum.models.Corrida;
import com.lmmartins.vrum.repositories.CorridaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

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

    public Long atualizarCorridaPeloId(Long corridaId, CorridaDTO corridaDto) {
        Corrida corrida = mapper.map(corridaDto, Corrida.class);
        corrida.setId(corridaId);
        return repository.save(corrida).getId();
    }

    public void deletarCorridaPeloId(Long corridaId) {
        repository.deleteById(corridaId);
    }
}
