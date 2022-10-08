package com.prueba.service.dto;

import com.prueba.domain.enums.GeneroType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmployeeRequest {
    private Integer id;
    private String identification;
    private String firstName;
    private String lastName;
    private String surnames;
    @Temporal(TemporalType.DATE)
    private Date birtDate;
    private Integer age;
    private double stature;
    private LocalDateTime createDate;
    private GeneroType generoType;
    private String statusCivil;
    private String dateAcceptation;
    private InstitutionDto institutionDto;
}
