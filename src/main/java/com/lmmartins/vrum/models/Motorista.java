package com.lmmartins.vrum.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Motorista extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modeloCarro;
    private Boolean status;

    public Motorista(Long id,
                     String nome,
                     LocalDate dataNasc,
                     String cpf,
                     String sexo,
                     String modeloCarro,
                     Boolean status) {
        super(nome, dataNasc, cpf, sexo);
        this.id = id;
        this.modeloCarro = modeloCarro;
        this.status = status;
    }
}