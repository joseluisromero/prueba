package com.prueba.service.dto;

import lombok.*;

import javax.persistence.Table;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DataEncryptApigeeRequest {
    private DataRequest data;
}
