package com.prueba;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.entity.Car;
import com.prueba.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@Component
@EnableScheduling
public class PruebaApplication {
    private static ObjectMapper objectMapper;


    {
        objectMapper = new ObjectMapper();
    }

    public static void main(String[] args) throws JsonProcessingException {
        //SpringApplication.run(PruebaApplication.class, args);
        ApplicationContext context = SpringApplication.run(PruebaApplication.class, args);
		/*CarService servicio=context.getBean(CarService.class);
		System.out.println("inicio");
		Set<Car> listCars = servicio.getlistCar();
		System.out.println("fin "+listCars.toString());
		// creating a Stream of strings
		Stream<String> s = Stream.of("Geeks",
				"for",
				"GeeksforGeeks",
				"Geeks Classes");
		System.out.println("Normal: "+s);
		// using Collectors toList() function
		List<String> myList = s.collect(Collectors.toList());

		// printing the elements
		System.out.println("en array: "+myList);
		*/
	/*
		boolean a=true;
		boolean b=true;
		if(a|b){
			System.out.println(" entro a|b "+a+" b:"+b);
		}else{
			System.out.println(" entro a|b "+a+" b:"+b);
		}
		*/
        //System.out.println(" entro "+a+" b:"+b);
        //para sumar valores a  una bandera  dentro del for
        /*AtomicLong val = new AtomicLong(0);
        for (Integer i = 0; i < 5; i++) {
            // Prints the updated value
            System.out.println("Previous value: "
                    + val);
            // Adds 8 and gets the previous value
            long res = val.getAndAdd(1);
        }

        Car car = new Car();
        car.setCarId(UUID.randomUUID());
        car.setEncendido(true);
        car.setModelo("Json");
        car.setNumPasajero(2);
        String userJson = objectMapper.writeValueAsString(car);
        System.out.println(" obj json "+userJson);*/
        //para tareas programadas
        //taskConciliacion();
/*
        // ArrayList con tamaño
        ArrayList<String> al = new ArrayList<String>();

// Añadir elementos a un ArrayList
        al.add("Victor");
        al.add("Luis");
        al.add("Elena");
        Iterator<String> it = al.iterator();
        String next =al.iterator().next();
        System.out.println("next "+next);*/
/*
        BigDecimal paymentCollections=BigDecimal.ZERO;
        BigDecimal negative=BigDecimal.TEN.negate();
        paymentCollections=paymentCollections.add(negative);
        paymentCollections=paymentCollections.add(new BigDecimal("5"));
        paymentCollections=paymentCollections.add(negative);

        System.out.println("valor "+paymentCollections);*/
        /*int anInt = new BigDecimal("12.6").intValue();
        System.out.println("entero "+anInt);*/
      /*
        List<String> cities = Arrays.asList("Milan", "London", "New York", "San Francisco");
        String citiesCommaSeparated = String.join(",", cities);
        System.out.println(citiesCommaSeparated);

        citiesCommaSeparated = cities.stream().collect(Collectors.joining(","));
        System.out.println(citiesCommaSeparated);*/
        String nombre="ECO_AMBATO";
        String temp=nombre.replace("_"," ");
        System.out.println("caded reemplazada: "+nombre);

    }

    //@Scheduled(cron = "0 2 * * * *")
    public static void taskConciliacion() {
        //Date fechaInicio=UtilDate.setearFecha(new Date(),0,0,0,0);
        //Date fechaFin =UtilDate.setearFecha(new Date(),11,0,0,0);
        //List<PaymentCollections> listConciliaciones=paymentCollectionsRepository.getReportConciliacion(fechaInicio,fechaFin);
        System.out.println("se ejecuto en este horario " + new Date() + "con total de registros ");
    }
}
