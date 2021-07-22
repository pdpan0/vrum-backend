package com.lmmartins.vrum.services;

import com.lmmartins.vrum.dto.CountDTO;
import com.lmmartins.vrum.dto.MotoristaDTO;
import com.lmmartins.vrum.enums.MotoristaStatus;
import com.lmmartins.vrum.exceptions.ValidacaoException;
import com.lmmartins.vrum.models.Motorista;
import com.lmmartins.vrum.repositories.MotoristaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public CountDTO getTotalMotoristas() {
        return new CountDTO("Motoristas", repository.count());
    }

    //Metódos de Criação
    public Long criarMotorista(MotoristaDTO motoristaDto) throws Exception {
        Motorista motorista = mapper.map(motoristaDto, Motorista.class);
        validarMotorista(motorista);
        return repository.save(motorista).getId();
    }

    //Metódos de Atualização
    public Long atualizarMotorista(Long motoristaId,
                                   MotoristaDTO motoristaDto) throws Exception {
        if(repository.existsById(motoristaId)) {
            Motorista motorista = mapper.map(motoristaDto, Motorista.class);
            validarMotorista(motorista);
            motorista.setId(motoristaId);
            return repository.save(motorista).getId();
        } else {
            throw new ValidacaoException("Motorista não existe.");
        }
    }

    public void atualizarStatusDoMotorista(Long motoristaId, MotoristaStatus status) throws Exception {
         if (!repository.existsById(motoristaId)) {
             throw new ValidacaoException("Motorista não existe");
         }
        repository.setMotoristaStatus(motoristaId, status.motoristaStatus);
    }

    //Metódos de Deleção.
    public void deletarMotoristaPorId(Long motoristaId) throws Exception {
        if (!repository.existsById(motoristaId)) {
            throw new ValidacaoException("Motorista não existe");
        }
        repository.deleteById(motoristaId);
    }

    //Validações
    private void validarMotorista(Motorista motorista) throws Exception {
        //TODO: Validar outros campos.
    }

    private void validarMotoristaPorCPF(String cpf) throws Exception {
        if(repository.existsByCpf(cpf)) {
            throw new ValidacaoException("CPF já existe.");
        }
    }
}
