package com.prueba.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DataEncryptApigeeResponse {
    private String bodyEncrypt;
    private String firme;
    private String request;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ascii;
}
