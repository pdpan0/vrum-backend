package com.lmmartins.vrum.services;

import com.lmmartins.vrum.dto.CorridaDTO;
import com.lmmartins.vrum.dto.CountDTO;
import com.lmmartins.vrum.exceptions.ValidacaoException;
import com.lmmartins.vrum.models.Corrida;
import com.lmmartins.vrum.models.Motorista;
import com.lmmartins.vrum.models.Passageiro;
import com.lmmartins.vrum.repositories.CorridaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
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

    //Metódos de Consultas
    public List<Corrida> getCorridasOrdenadas(Sort.Direction direction, String... properties) {
        return repository.findAll(Sort.by(direction, properties));
    }

    public List<Corrida> getCorridasPorPassageiro(Long passageiroId) {
        return repository.findByPassageiroId(passageiroId);
    }

    public List<Corrida> getCorridasPorMotorista(Long motoristaId) {
        return repository.findByMotoristaId(motoristaId);
    }

    public CountDTO getTotalCorridas() {
        return new CountDTO("Corridas", repository.count());
    }

    //Metódos de Criação
    public Long criarCorrida(CorridaDTO corridaDto) throws Exception {
        validarCorrida(corridaDto);
        Corrida corrida = mapper.map(corridaDto, Corrida.class);
        return repository.save(corrida).getId();
    }
    //Metódos de Atualização
    public Long atualizarCorrida(Long corridaId, CorridaDTO corridaDto) throws Exception {
        if (repository.existsById(corridaId)) {
            validarCorrida(corridaDto);
            Corrida corrida = mapper.map(corridaDto, Corrida.class);
            corrida.setId(corridaId);
            return repository.save(corrida).getId();
        } else {
            throw new ValidacaoException("Corrida não existe.");
        }
    }

    public void deletarCorrida(Long corridaId) throws Exception {
        if (!repository.existsById(corridaId)) {
            throw new ValidacaoException("Corrida não existe");
        }
        repository.deleteById(corridaId);
    }

    public void deletarCorridaPorMotorista(Long motoristaId) throws Exception {
            repository.deleteCorridaByMotoristaId(motoristaId);
    }

    public void deletarCorridaPorPassageiro(Long passageiroId) throws Exception {
        repository.deleteCorridaByPassageiroId(passageiroId);
    }

    //Validações
    private void validarCorrida(CorridaDTO corrida) throws Exception {
        if(!motoristaService.existMotorista(corrida.getMotoristaId())) {
            throw new ValidacaoException("Motorista é obrigatório.");
        }

        if(!passageiroService.existPassageiro(corrida.getPassageiroId())) {
            throw new ValidacaoException("Passageiro é obrigatório.");
        }

        if(corrida.getPrecoTotal().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidacaoException("Preço total não pode ser menor que 0.");
        }
    }
}
