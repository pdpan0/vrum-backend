package com.lmmartins.vrum.services;

import com.lmmartins.vrum.dto.CorridaDTO;
import com.lmmartins.vrum.dto.MotoristaDTO;
import com.lmmartins.vrum.enums.MotoristaStatus;
import com.lmmartins.vrum.exceptions.ValidacaoException;
import com.lmmartins.vrum.models.Motorista;
import com.lmmartins.vrum.models.Passageiro;
import com.lmmartins.vrum.repositories.MotoristaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class MotoristaService {

    @Autowired
    private MotoristaRepository repository;

    @Autowired
    private ModelMapper mapper;

    //Metódos de Consultas
    //TODO: Sort, Paginação, Filtros
    public List<Motorista> getMotoristas() {
        return repository.findAll();
    }

    public Optional<Motorista> getMotoristasPorId(Long motoristaId) {
        return repository.findById(motoristaId);
    }

    public Optional<Motorista> getMotoristaByCpf(String cpf) {
        return repository.getMotoristaByCpf(cpf);
    }
    //Metódos de Criação
    public Long criarMotorista(MotoristaDTO motoristaDto) throws Exception {
        validarMotorista( motoristaDto);
        Motorista motorista = mapper.map(motoristaDto, Motorista.class);
        return repository.save(motorista).getId();
    }

    //Metódos de Atualização
    public Long atualizarMotorista(Long motoristaId,
                                   MotoristaDTO motoristaDto) throws Exception {
        validarMotorista(motoristaDto);
        Motorista motorista = mapper.map(motoristaDto, Motorista.class);
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

    private void validarMotorista(MotoristaDTO motoristaDto) throws Exception {
        Optional<Motorista> motorista= repository.getMotoristaByCpf(motoristaDto.getCpf());

        if(motorista.isPresent()) {
            throw new ValidacaoException("Motorista já existe.");
        }
    }
}
