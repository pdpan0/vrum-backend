package com.lmmartins.vrum.services;

import com.lmmartins.vrum.dto.CorridaDTO;
import com.lmmartins.vrum.dto.ValidacaoDTO;
import com.lmmartins.vrum.exceptions.ValidacaoException;
import com.lmmartins.vrum.models.Corrida;
import com.lmmartins.vrum.models.Motorista;
import com.lmmartins.vrum.models.Passageiro;
import com.lmmartins.vrum.repositories.CorridaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class CorridaService {
    @Autowired
    private CorridaRepository repository;

    @Autowired
    private MotoristaService motoristaService;
    @Autowired
    private PassageiroService passageiroService;

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

    public Long criarCorrida(CorridaDTO corridaDto) throws Exception {
        validarCorrida(corridaDto);
        Corrida corrida = mapper.map(corridaDto, Corrida.class);
        return repository.save(corrida).getId();
    }

    public Long atualizarCorrida(Long corridaId, CorridaDTO corridaDto) throws Exception {
        validarCorrida(corridaDto);
        Corrida corrida = mapper.map(corridaDto, Corrida.class);
        corrida.setId(corridaId);
        return repository.save(corrida).getId();
    }

    public void deletarCorrida(Long corridaId) {
        repository.deleteById(corridaId);
    }

    private void validarCorrida(CorridaDTO corrida) throws Exception {
        Optional<Motorista> motorista =
                motoristaService.getMotoristasPorId(corrida.getMotoristaId());

        if(!motorista.isPresent()) {
            throw new ValidacaoException("Motorista é obrigatório.");
        }

        Optional<Passageiro> passageiro =
                passageiroService.getPassageiroPorId(corrida.getPassageiroId());

        if(!passageiro.isPresent()) {
            throw new ValidacaoException("Passageiro é obrigatório.");
        }

        if(corrida.getPrecoTotal().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidacaoException("Preço total não pode ser menor que 0.");
        }
    }
}
