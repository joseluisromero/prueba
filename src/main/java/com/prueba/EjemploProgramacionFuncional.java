package com.prueba;

import com.prueba.domain.Car;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EjemploProgramacionFuncional {
    public static void main(String[] args) {
        Car car = new Car(UUID.randomUUID(), true, "Json", 2);
        Car car2 = new Car(UUID.randomUUID(), false, "xml", 5);
        Car car3 = new Car(UUID.randomUUID(), false, "xml", null);
        List<Car> listCar = new ArrayList<>();
        listCar.add(car);
        listCar.add(car2);
        listCar.add(car3);
        //para filtrar los objetos  no null y imprimir por consola el campo pasajero
        listCar.stream().filter((car1) -> Objects.nonNull(car1)).map(Car::getNumPasajero).forEach(System.out::println);
        //para agregarlo a otra lista los objetos nonNull
        List<Car> carList = listCar.stream().filter(car1 -> Objects.nonNull(car1)).collect(Collectors.toList());
        //Para agregar a otra lista los objetos que tienen el pajaro no null
        List<Car> carListPasajero = listCar.stream().filter(car1 -> Objects.nonNull(car1.getNumPasajero())).collect(Collectors.toList());
        //generar una lista de 100 numeros
        IntStream intStream = IntStream.range(0, 100);
        //intStream.forEach(System.out::println);
        //sumar los numero impares
        intStream.filter(value -> value % 2 == 0).map(operand -> operand * operand).reduce(Integer::sum).ifPresent(System.out::println);
        //OptionalInt suma=intStream.filter(value -> value%2==0).map(operand -> operand*operand).reduce(Integer::sum).ifPresent(System.out::println);
        int total = 0;
        float totalSimple = 0;
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                totalSimple = (float) (totalSimple +Math.pow(i, 2));
            }
            total += Math.pow(i, 2);
        }
        System.out.println(total + " total simple " + totalSimple);
    }
}
