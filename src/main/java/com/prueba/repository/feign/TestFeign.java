package com.prueba.repository.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;

@FeignClient(name = "xxx",url = "http://localhost:8080")
public interface TestFeign {
    @PatchMapping("/test/patch")
    ResponseEntity<String> probandoRequestPatch();
}
