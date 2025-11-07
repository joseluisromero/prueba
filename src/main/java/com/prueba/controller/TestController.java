package com.prueba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Objects;
import java.util.stream.IntStream;

@RestController
@Slf4j
@RequestMapping(path = "/test")
public class TestController {

    @GetMapping(path = "/addDayDate")
    public ResponseEntity<?> sumarDiasAFechaActual(@RequestParam() int dayAdd, @RequestParam() boolean soloDiaLaboral) {
        var fechaInicial = Calendar.getInstance();
        Calendar response = sumarDiasAFechaActual(fechaInicial, dayAdd, soloDiaLaboral);
        return new ResponseEntity<>(response.getTime(), HttpStatus.OK);
    }

    @GetMapping(path = "/addTest")
    public ResponseEntity<?> add(@RequestParam() int dayAdd) {
        Integer sum = 0;
        for (int i = 1; i <= dayAdd; i++) {
            sum = sum +i;
        }
        IntStream intStream = IntStream.range(1, 101);
        //intStream.forEach(System.out::println);
        //sumar los numero impares
        Integer t=intStream.reduce(Integer::sum).getAsInt();

        System.out.println("Total:" + sum);
        return new ResponseEntity<>( intStream.reduce(Integer::sum).getAsInt(), HttpStatus.OK);
    }

    /**
     * Método para sumar dias a fecha
     *
     * @param fechaInicial
     * @param diasASumar
     * @param soloDiaLaboral
     * @return
     */
    private Calendar sumarDiasAFechaActual(Calendar fechaInicial, int diasASumar, boolean soloDiaLaboral) {
        if (Objects.isNull(fechaInicial))
            fechaInicial = Calendar.getInstance();
        if (!soloDiaLaboral) {
            fechaInicial.add(Calendar.DAY_OF_MONTH, diasASumar);
        } else {
            while (diasASumar > 1) {

                if (fechaInicial.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                        || fechaInicial.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {

                    fechaInicial.add(Calendar.DAY_OF_MONTH, 1);
                } else {
                    diasASumar--;
                    fechaInicial.add(Calendar.DAY_OF_MONTH, 1);
                }

            }
        }
        log.info(fechaInicial.getTime().toString());
        return fechaInicial;
    }
}
