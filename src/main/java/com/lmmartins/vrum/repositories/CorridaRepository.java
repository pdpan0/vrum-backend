package com.lmmartins.vrum.repositories;

import com.lmmartins.vrum.models.Corrida;
import com.lmmartins.vrum.models.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

public interface CorridaRepository extends JpaRepository<Corrida, Long> {

    List<Corrida> findByPassageiroId(Long passageiroId);
    List<Corrida> findByMotoristaId(Long motoristaId);

    @Transactional
    @Modifying
    @Query("delete from Corrida c where c.motorista.id = :motoristaId")
    void deleteCorridaByMotoristaId(@Param("motoristaId") Long motoristaId);
    @Transactional
    @Modifying
    @Query("delete from Corrida c where c.passageiro.id = :passageiroId")
    void deleteCorridaByPassageiroId(@Param("passageiroId") Long passageiroId);
}
