package com.prueba;


import com.prueba.entity.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class ExpresionesLambdas {

    public static void main(String[] args) {
        Car car = new Car(UUID.randomUUID(),true,"Json",2);
        Car car2 = new Car(UUID.randomUUID(),false,"xml",5);
        List<Car> listCar=new ArrayList<>();
        listCar.add(car);
        listCar.add(car2);
        listCar.forEach((item)-> System.out.println("car: "+item));
        listCar.forEach((car1)->System.out.println("* "+car1.getModelo()));
        List<Car> listCarFilter=listCar.stream().parallel().filter((item)->(item.isEncendido()==true && item.getModelo().equalsIgnoreCase("json"))).collect(Collectors.toList());
        Long count=listCar.stream().filter((item)->(item.isEncendido()==true && item.getModelo().equalsIgnoreCase("json"))).count();
        listCarFilter.forEach((item)-> System.out.println("filter: "+item));
        System.out.println("count: "+count);
        Integer sum=listCar.stream().mapToInt(Car::getNumPasajero).sum();
        System.out.println("suma con mapToInt: "+sum);
        Map<UUID,String> listMap=listCar.stream().collect(Collectors.toMap(Car::getCarId,Car::getModelo));
        System.out.println("map: "+listMap);
        String s1 = "";
        System.out.println(s1.isBlank() + " " + s1.isEmpty());

        String s2 = " ";
        System.out.println(s2.isBlank() + " " + s2.isEmpty());

        String s3 = "Program";
        System.out.println(s3.isBlank() + " " + s3.isEmpty());

        String s4 = new String();
        System.out.println(s4.isBlank() + " " + s4.isEmpty());

        String s5 = new String("");
        System.out.println(s5.isBlank() + " " + s5.isEmpty());

        String s6 = new String(" ");
        System.out.println(s6.isBlank() + " " + s6.isEmpty());

        String s7 = new String("Program");
        System.out.println(s7.isBlank() + " " + s7.isEmpty());

    }
}
