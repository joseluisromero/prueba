package com.prueba.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FormatoFechaRequest {
    @JsonProperty("fecha_util")
    private Date fechaUtil;
    @JsonProperty("fecha_local")
    private LocalDateTime fechaLocal;
    @JsonProperty("fecha_string")
    private String fechaString;

    @Override
    public String toString() {
        return "FormatoFechaRequest{" +
                "fechaUtil=" + fechaUtil +
                ", fechaLocal=" + fechaLocal +
                ", fechaString='" + fechaString + '\'' +
                '}';
    }
}
