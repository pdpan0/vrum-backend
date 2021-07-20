package com.lmmartins.vrum.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Corrida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;
    @ManyToOne
    @JoinColumn(name = "passageiro_id")
    private Passageiro passageiro;

    private BigDecimal precoTotal;
}
