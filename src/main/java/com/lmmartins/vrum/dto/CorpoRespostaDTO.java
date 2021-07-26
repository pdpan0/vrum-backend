package com.lmmartins.vrum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CorpoRespostaDTO {
    private Integer code;
    private String type;
    private String message;
    private String objectId;
}
