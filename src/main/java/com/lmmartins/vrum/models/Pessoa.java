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
    private String nome;
    private LocalDate dataNasc;
    private String cpf;
    private String sexo;
}
