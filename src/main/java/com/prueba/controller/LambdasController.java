package com.prueba.controller;

import com.prueba.service.LambdasService;
import com.prueba.service.dto.Person;
import com.prueba.service.dto.Travel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/lambdas")
@Slf4j
@RequiredArgsConstructor
public class LambdasController {
    @Autowired
    private LambdasService lambdasService;

    @GetMapping(value = "/persons")
    public ResponseEntity<List<String>> getTravelsForPersonName() {

        return new ResponseEntity<List<String>>(lambdasService.getTravelsForPersonNameMap(), HttpStatus.OK);
    }

    @GetMapping(value = "/person/{name}")
    public ResponseEntity<List<Person>> getTravelsForPerson(@PathVariable(name = "name") String name) {

        return new ResponseEntity<List<Person>>(lambdasService.getTravelsForPersonMap(name), HttpStatus.OK);
    }

    @GetMapping(value = "/travels")
    public ResponseEntity<List<String>> getTravelsForPerson() {
        return new ResponseEntity<List<String>>(lambdasService.getTravels(), HttpStatus.OK);
    }

    @GetMapping(value = "/travelsDistinct")
    public ResponseEntity<List<String>> getTravelsDistinct() {
        return new ResponseEntity<List<String>>(lambdasService.getTravelsDistinct(), HttpStatus.OK);
    }

    @GetMapping(value = "/travelsDistinctCount")
    public ResponseEntity<Long> getTravelsDistinctCount() {
        return new ResponseEntity<Long>(lambdasService.getTravelsDistinctCount(), HttpStatus.OK);
    }
    @GetMapping(value = "/countryForPerson")
    public ResponseEntity<Map<String,Integer>> getCountryForPerson() {
        return new ResponseEntity<Map<String,Integer>>(lambdasService.getCountryForPerson(), HttpStatus.OK);
    }


}
