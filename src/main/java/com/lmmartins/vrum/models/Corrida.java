package com.lmmartins.vrum.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Corrida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //TODO: Não pode ser nulo.
    @ManyToOne
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;

    //TODO: Não pode ser nulo.
    @ManyToOne
    @JoinColumn(name = "passageiro_id")
    private Passageiro passageiro;

    @Column(unique = true, nullable = false)
    private BigDecimal precoTotal;
}
