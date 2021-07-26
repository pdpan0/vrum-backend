package com.lmmartins.vrum.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Passageiro extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "passageiro", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<Corrida> corridas;

    public Passageiro(Long id,
                      String nome,
                      LocalDate dataNasc,
                      String cpf,
                      String sexo) {
        super(nome, dataNasc, cpf, sexo);
        this.id = id;
    }
}
