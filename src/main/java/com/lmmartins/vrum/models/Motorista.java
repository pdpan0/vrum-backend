package com.lmmartins.vrum.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Motorista extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String modeloCarro;
    @Column(nullable = false)
    private Boolean status;
    @OneToMany(mappedBy = "motorista", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<Corrida> corridas;

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
