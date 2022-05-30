package com.prueba;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.logging.*;

public class TareaProgramada {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Inicio exitoso de la aplicación!");


//Instanciamos nuestra clase Timer
        Timer temporizador = new Timer();

        while (true) { /*El proceso se debe ejecutar infinitamente, ya que se debe validar que la hora sea la correcta en cada ciclo, se podría establecer para que después de cierto día se detenga, sin embargo para este ejemplo dejaremos infinito mientras se ejecute la aplicación*/

            //Estableceremos la hora, minutos y segundo en que queremos que se ejecute nuestro proceso
            Date dia = new Date();
            Calendar hora7 = Calendar.getInstance();
            hora7.set(Calendar.HOUR_OF_DAY, 16); // 11 am del día
            hora7.set(Calendar.MINUTE,43); // Con 29 minutos
            hora7.set(Calendar.SECOND, 0); // Con 0 segundos

            Date horaEjecucion7 = hora7.getTime();

            Thread.sleep(1000); /*En algunos casos me he dado cuenta que sin el sleep de un segundo, la ejecución de la tarea no funciona correctamente*/

            if (dia.toString().contains(horaEjecucion7.toString())) {//Verificamos si la hora actual es la hora de ejecución de la tarea
                //LOG_MONITOREO.log(Level.INFO, dia + " Script de ARCHIVE Ejecutado");//Escribimos la ejecución en el log
                temporizador.schedule(new Temporizador(), horaEjecucion7); //Ejecutamos la tarea programada
            }
            /*Por último para evitar un crecimiento en el uso de la memoria de la aplicación, cada instancia la vuelvo a null en cada ciclo, así se mantiene la cantidad de memoria usada por la aplicación*/
            dia = null;
            hora7 = null;
            horaEjecucion7 = null;
            System.out.println("termino");
        }
    }
}
