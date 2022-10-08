package com.prueba.service.impl;

import com.prueba.domain.Car;
import com.prueba.service.CarService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
@Service
public class CarServiceImpl implements CarService {

    @Override
    public Set<Car> getlistCar() {
        Car car1=new Car(UUID.randomUUID(),true,"KIA",6);
        Car car2=new Car(UUID.randomUUID(),true,"HYNDAY",5);
        Set<Car>listaCarros=new HashSet<>();
        listaCarros.add(car1);
        listaCarros.add(car2);

        return listaCarros;
    }

    @Override
    public Set<Car> getCarById(UUID id) {
        return null;
    }
}
