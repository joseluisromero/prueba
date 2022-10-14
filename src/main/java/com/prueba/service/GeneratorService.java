package com.prueba.service;

import com.prueba.service.dto.ClientInfoDto;
import com.prueba.service.dto.ClientInfoResponseDto;
import reactor.core.publisher.Mono;

public interface GeneratorService {
    ClientInfoDto getClientInfoAdditional(String hash);
    Mono<ClientInfoDto> getClientInfoAdditionalMono(String hash);
    Mono<ClientInfoResponseDto> getClientInfoConv(String hash);
}
