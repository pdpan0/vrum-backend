package com.lmmartins.vrum.dto;

import com.lmmartins.vrum.enums.MotoristaStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MotoristaDTO {
    private String nome;
    private LocalDate dataNasc;
    private String cpf;
    private String sexo;
    private String modeloCarro;
    private MotoristaStatus status;
}