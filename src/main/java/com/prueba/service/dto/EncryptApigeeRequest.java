package com.prueba.service.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EncryptApigeeRequest {
    private String opportunityId;
    private String firme;
    private String ruta;
    private String cellPhone;
}
