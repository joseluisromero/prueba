package com.prueba.service.dto.rsa;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PayloadRSA {
    private String attribute;
    private String value;
}
