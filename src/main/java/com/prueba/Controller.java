package com.prueba;

import com.prueba.entity.Car;
import com.prueba.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class Controller {
    @Autowired
    CarService carService;
    @RequestMapping(value = "/", method= RequestMethod.GET)
    public Set<Car> obtener(){
       Set<Car> listaCar= carService.getlistCar();
        System.out.println("lista: "+listaCar.size());
        return listaCar;
    }
}
