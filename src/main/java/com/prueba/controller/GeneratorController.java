package com.prueba.controller;

import com.prueba.service.GeneratorService;
import com.prueba.service.dto.ClientInfoDto;
import com.prueba.service.dto.ClientInfoResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/apiExternal")
public class GeneratorController {
    private final GeneratorService generatorService;

    @GetMapping("/{hash}")
    public ResponseEntity<ClientInfoDto> getClientInfo(@Valid @PathVariable String hash) {
        return new ResponseEntity<>(generatorService.getClientInfoAdditional(hash), HttpStatus.OK);
    }
    @GetMapping("/mono/{hash}")
    public Mono<ClientInfoDto> getClientInfoMono(@Valid @PathVariable String hash) {
        return generatorService.getClientInfoAdditionalMono(hash);
    }
    @GetMapping("/monoConverter/{hash}")
    public Mono<ClientInfoResponseDto> getClientInfoMonoConver(@Valid @PathVariable String hash) {
        return generatorService.getClientInfoConv(hash);
    }

}
