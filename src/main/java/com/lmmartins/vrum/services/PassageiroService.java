package com.lmmartins.vrum.services;

import com.lmmartins.vrum.dto.CountDTO;
import com.lmmartins.vrum.dto.PassageiroDTO;
import com.lmmartins.vrum.exceptions.ValidacaoException;
import com.lmmartins.vrum.models.Motorista;
import com.lmmartins.vrum.models.Passageiro;
import com.lmmartins.vrum.repositories.PassageiroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassageiroService {
    @Autowired
    private PassageiroRepository repository;

    @Autowired
    private ModelMapper mapper;

    //Metódos de Consultas
    //TODO: Sort, Paginação, Filtros
    public List<Passageiro> getPassageiros() {
        return repository.findAll();
    }

    public Optional<Passageiro> getPassageiroPorId(Long passageiroId) {
        return repository.findById(passageiroId);
    }

    public CountDTO getTotalPassageiros() {
        return new CountDTO("Passageiros", repository.count());
    }

    //Metódos de Criação
    public Long criarPassageiro(PassageiroDTO passageiroDto) throws Exception {
        Passageiro passageiro = mapper.map(passageiroDto, Passageiro.class);
        validarPassageiro(passageiro);
        return repository.save(passageiro).getId();
    }

    //Metódos de Atualização
    public Long atualizarPassageiro(Long passageiroId,
                                   PassageiroDTO passageiroDto) throws Exception {
        if (repository.existsById(passageiroId)) {
            Passageiro passageiro = mapper.map(passageiroDto, Passageiro.class);
            validarPassageiro(passageiro);
            passageiro.setId(passageiroId);
            return repository.save(passageiro).getId();
        } else {
            throw new ValidacaoException("Passageiro não existe");
        }
    }

    //Metódos de Deleção.
    public void deletarPassageiroPorId(Long passageiroId) throws Exception {
        if (!repository.existsById(passageiroId)) {
            throw new ValidacaoException("Passageiro não existe");
        }
        repository.deleteById(passageiroId);
    }

    //Validações
    private void validarPassageiro(Passageiro passageiro) throws Exception {
        //TODO: Validar outros campos.
    }

    private void validarPassageiroPorCPF(String cpf) throws Exception {
        if(repository.existsByCpf(cpf)) {
            throw new ValidacaoException("CPF já existe.");
        }
    }
}
