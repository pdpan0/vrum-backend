package com.lmmartins.vrum.repositories;

import com.lmmartins.vrum.models.Corrida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CorridaRepository extends JpaRepository<Corrida, Long> {

    List<Corrida> findByPassageiroId(Long passageiroId);
    List<Corrida> findByMotoristaId(Long motoristaId);

    //TODO: Avaliar se necessário.
    List<Corrida> findByPassageiroIdOrMotoristaId(Long passageiroId,
                                                   Long motoristaId);
}
