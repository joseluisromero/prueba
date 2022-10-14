package com.prueba.service.dto;

import com.sun.jdi.event.StepEvent;
import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientInfoResponseDto {

    private String identification;
    private String unmaskedIdentification;
    private String fullNames;
    private String cardType;
    private Date toDay;
}
