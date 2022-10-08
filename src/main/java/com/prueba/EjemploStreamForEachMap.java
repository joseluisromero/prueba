package com.prueba;

import com.prueba.domain.Car;

import java.util.*;

public class EjemploStreamForEachMap {
    public static void main(String[] args) {
        Car car = new Car(UUID.randomUUID(), true, "Json", 2);
        Car car2 = new Car(UUID.randomUUID(), false, "xml", 5);
        Car car3 = new Car(UUID.randomUUID(), false, "text", 7);
        List<Car> listCar = new ArrayList<>();
        listCar.add(car);
        listCar.add(car2);
        listCar.add(car3);
        //Diferencia estre forEach vs StreamForEach
        //1.) forEach
        listCar.forEach((c) -> {
            System.out.println(c.getModelo());
            System.out.println(String.valueOf(c.getNumPasajero()));
            System.out.println(String.valueOf(c.isEncendido()));
        });
        //2.) es Cierto que Stream podemos hacer uso de map o filter que le dan una mayor flexibilidad
        listCar.stream().forEach((c) -> {
            System.out.println(c.getModelo());
            System.out.println(String.valueOf(c.getNumPasajero()));
            System.out.println(String.valueOf(c.isEncendido()));
        });

        //3. pero lo que si es  notoria seria si tenemos un  diccionario con Personas (hashmap)
        Map<String, Car> map = new HashMap<>();
        map.put(car.getModelo(), car);
        map.put(car2.getModelo(), car);
        map.put(car3.getModelo(), car);

        //3.1 recorreclo con forEach
        for (String model : map.keySet()) {
            Car carSeleccionado = map.get(model);
            System.out.println(carSeleccionado.getModelo());
            System.out.println(String.valueOf(carSeleccionado.getNumPasajero()));
            System.out.println(String.valueOf(carSeleccionado.isEncendido()));
        }

        //3.2 pero si lo hacemos  forEach
        map.forEach((k, v) -> {
            System.out.println(v.getModelo());
            System.out.println(v.getNumPasajero());
            System.out.println(v.isEncendido());
        });
    }
}
