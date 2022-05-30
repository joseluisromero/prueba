package com.prueba.service;

import com.prueba.entity.Car;

import java.util.Set;
import java.util.UUID;

public interface CarService {
    Set<Car> getlistCar();
    Set<Car>getCarById(UUID id);
}
