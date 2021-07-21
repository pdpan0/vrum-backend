package com.lmmartins.vrum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidacaoDTO {
    private Boolean sucesso;
    private String mensagemErro;
}
