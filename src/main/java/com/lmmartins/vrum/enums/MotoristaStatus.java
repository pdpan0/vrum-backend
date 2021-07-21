package com.lmmartins.vrum.enums;

public enum MotoristaStatus {
    ATIVO(true), INATIVO(false);

    public boolean motoristaStatus;

    MotoristaStatus(Boolean valor) {
        motoristaStatus = valor;
    }
}
