package com.prueba.domain.enums;

import lombok.Getter;

@Getter
public enum GeneroType {
    MASCULINE("Masculino"),
    FEMENINO("Femenino");

    private String name;

    GeneroType(String name) {
        this.name = name;
    }

}
