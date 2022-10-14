package com.prueba.service.dto;

import lombok.*;

import java.util.Date;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ClientInfoDto {
    private Long id;
    private String identification;
    private String unmaskedIdentification;
    private String cif;
    private String identificationHash;
    private String firstName;
    private String secondName;

    private String firstSurname;

    private String secondSurname;

    private String cardType;

    private String cardProgram;

    private String cardBrand;

    private Double cardLimit;

    private String cardName;

    private String phoneNumber;

    private String address;

    private String email;

    private Date createdTime;
    private String cardNumber;
    private Set<AdditionalRequestDto> requests;

    public String getFullName() {
        StringBuilder builder = new StringBuilder();
        if(!firstName.equals("")) builder.append(firstName);
        if(!secondName.equals("")) builder.append(" ").append(secondName);
        if(!firstSurname.equals("")) builder.append(" ").append(firstSurname);
        if(!secondSurname.equals("")) builder.append(" ").append(secondSurname);
        return builder.toString();
    }
    }
