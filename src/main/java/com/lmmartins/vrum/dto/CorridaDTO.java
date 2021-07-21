package com.lmmartins.vrum.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CorridaDTO {
    private Long motoristaId;
    private Long passageiroId;
    private BigDecimal precoTotal;
}
