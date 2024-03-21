package com.prueba.configuration;

import feign.Contract;
//import feign.okhttp.OkHttpClient;
import org.springframework.cloud.openfeign.clientconfig.FeignClientConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public FeignClientConfigurer feignClientConfigurer() {
        return new FeignClientConfigurer() {
            @Override
            public boolean inheritParentConfiguration() {
                return false;
            }
        };
    }
    /*

    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }
    */

    /*@Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }*/
}
