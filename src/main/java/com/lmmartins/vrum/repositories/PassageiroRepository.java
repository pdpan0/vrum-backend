package com.lmmartins.vrum.repositories;

import com.lmmartins.vrum.models.Passageiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassageiroRepository extends JpaRepository<Passageiro, Long> {
    Boolean existsByCpf(String cpf);
}
