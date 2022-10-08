package com.prueba.service.dto;

import com.prueba.domain.enums.GeneroType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmployeeResponse {
    private String identification;
    private String firstName;
    private String lastName;
    private String surnames;
    private Date birtDate;
    private Integer age;
    private double stature;
    private GeneroType generoType;
    private String institutionName;
    private String statusCivil;
    private String dateAcceptation;
    private String ciNames;
    private Date createDate;
}
