package com.prueba.util.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ApiWebClient {

    @Value("${url_base}")
    private String urlBase;

    public WebClient webClient() {
        return WebClient.builder().baseUrl(urlBase.trim().replace("\"","")).build();
    }


}
