package com.prueba.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorInfo {
    private String path;
    private LocalDateTime timestamp;
    private String message;
    private String code;
}
