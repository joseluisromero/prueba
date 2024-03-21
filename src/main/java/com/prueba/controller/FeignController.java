package com.prueba.controller;

import com.prueba.repository.feign.TestFeign;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/feign")
@CrossOrigin
@RequiredArgsConstructor
public class FeignController {

    private final TestFeign testFeign;

    @GetMapping("/find-feign")
    public ResponseEntity messagePatch() {

        ResponseEntity<String> response = null;
        try {
            response = testFeign.probandoRequestPatch();
        } catch (FeignException exception) {
            log.error("exception: {}", exception);

        }


        return new ResponseEntity<>("Cadena devuelta entre micros: "+response.getBody().toString(), HttpStatus.OK);


    }

}