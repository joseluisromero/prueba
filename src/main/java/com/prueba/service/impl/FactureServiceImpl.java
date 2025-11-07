package com.prueba.service.impl;

import com.prueba.domain.Facture;
import com.prueba.repository.FactureRepository;
import com.prueba.service.FactureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class FactureServiceImpl implements FactureService {
    private final FactureRepository factureRepository;

    @Override
    public Facture create(Facture facture) {
        log.info("facture in memory: {}",facture);
        facture.getDetails().stream().forEach(detail -> {
            detail.setTotal(detail.getUnit()*detail.getValue());
        });
        facture = factureRepository.save(facture);
        log.info("facture in data base: {}",facture);
        return facture;
    }
}
