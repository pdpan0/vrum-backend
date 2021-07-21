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

    @ManyToOne(optional = false)
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;

    @ManyToOne(optional = false)
    @JoinColumn(name = "passageiro_id")
    private Passageiro passageiro;

    @Column(nullable = false)
    private BigDecimal precoTotal;
}
