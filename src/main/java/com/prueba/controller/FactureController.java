package com.prueba.controller;

import com.prueba.domain.Facture;
import com.prueba.service.FactureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/facture")
public class FactureController {
    private final FactureService factureService;

    @PostMapping("/create")
    public ResponseEntity<Facture> getClientInfo(@Valid @RequestBody Facture facture) {
        return new ResponseEntity<>(factureService.create(facture), HttpStatus.CREATED);
    }


}
