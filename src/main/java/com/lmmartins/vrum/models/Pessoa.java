package com.lmmartins.vrum.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Pessoa {
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private LocalDate dataNasc;
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String sexo;
}
