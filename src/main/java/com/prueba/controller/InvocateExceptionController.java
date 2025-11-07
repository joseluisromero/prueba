package com.prueba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@RestController
@Slf4j
@RequestMapping(path = "/exception")
public class InvocateExceptionController {

    @PostMapping(path = "/print-logs")
    public ResponseEntity<?> sumarDiasAFechaActual(@RequestBody String dato) {
        var fechaInicial = Calendar.getInstance();
        log.info(dato);
        int size=proceess(dato);
        return new ResponseEntity<>(size, HttpStatus.OK);
    }

    private int proceess(String dato) {
        int size=dato.length();
        String d=new SimpleDateFormat("yyyy-mm-dd").format(dato);
        log.info("longitud del dato {}", size);
        return size;
    }
}
