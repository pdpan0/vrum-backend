package com.lmmartins.vrum.repositories;

import com.lmmartins.vrum.models.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MotoristaRepository extends JpaRepository<Motorista, Long> {
    @Transactional
    @Modifying
    @Query("update Motorista m set m.status = :status where m.id = :motoristaId")
    Integer setMotoristaStatus(@Param("motoristaId") Long motoristaId,
                               @Param("status") Boolean status);

    Boolean existsByCpf(String cpf);
}
