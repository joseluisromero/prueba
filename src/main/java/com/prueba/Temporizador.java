package com.prueba;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.TimerTask;

public class Temporizador extends TimerTask {
    @Override
    public void run() {
        try {
            String linea;
            //Colocamos la ruta del archivo .bat que ejecutaremos
            Process p = Runtime.getRuntime().exec("C:\\Users\\jose.romerO\\Desktop\\tarea_programada.txt");
            //Definimos un BufferedReader para leer la impresión que realice la ejecución de nuestro script
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((linea = input.readLine()) != null) {
                //Mientras que nuestro input imprima data, imprimeremos la salida por consola
                System.out.println(linea);
            }
            input.close(); //Cerramos el input
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
