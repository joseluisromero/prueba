package com.prueba.service.impl;

import com.prueba.service.GeneratorService;
import com.prueba.service.dto.ClientInfoDto;
import com.prueba.service.dto.ClientInfoResponseDto;
import com.prueba.service.mapper.GeneratorMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class GeneratorServiceImpl implements GeneratorService {

    @Value("${consumer_api_external}")
    private String path;

    private final GeneratorMapper generatorMapper;

    @Override
    @Transactional
    public ClientInfoDto getClientInfoAdditional(String hash) {
        log.info("value {}", path);
        getClientInfoAdditionalRestTemplate(hash);
        Mono<ClientInfoDto> resp = WebClient.create()
                .get()
                .uri(path.replace("\"", "") + "/additionalClientInfo/hash/" + hash)
                .retrieve()
                .bodyToMono(ClientInfoDto.class);

        return null;
    }

    @Override
    @Transactional
    public Mono<ClientInfoDto> getClientInfoAdditionalMono(String hash) {
        log.info("value {}", path);

        Mono<ClientInfoDto> resp = WebClient.create()
                .get()
                .uri(path.replace("\"", "") + "/additionalClientInfo/hash/" + hash)
                .retrieve()
                .bodyToMono(ClientInfoDto.class);
        resp.subscribe(clientInfoDto -> log.info(clientInfoDto.getIdentification()));
        return resp;
    }

    @Override
    public Mono<ClientInfoResponseDto> getClientInfoConv(String hash) {
        log.info("value {}", path);

        ClientInfoDto clientInfoDto=getClientGenerator(hash).block();
        //return Mono.just(clientInfoResponseDto);
        return Mono.just(generatorMapper.toAdditionalClientInfoResponseDto(clientInfoDto));
    }

    public Mono<ClientInfoDto> getClientGenerator(String hash) {
        log.info("value {}", path);

        Mono<ClientInfoDto> resp = WebClient.create()
                .get()
                .uri(path.replace("\"", "") + "/additionalClientInfo/hash/" + hash)
                .retrieve()
                .bodyToMono(ClientInfoDto.class);
        return resp;
    }

    @Transactional
    private ClientInfoDto getClientInfoAdditionalRestTemplate(String hash) {
        log.info("value {}", path);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity responseEntity = restTemplate.exchange(path.replace("\"", "") + "/additionalClientInfo/hash/" + hash, HttpMethod.GET, entity, String.class);
        ClientInfoDto clientInfoDto = (ClientInfoDto) responseEntity.getBody();
        return clientInfoDto;
    }
}
