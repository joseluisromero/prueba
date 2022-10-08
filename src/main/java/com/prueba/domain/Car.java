package com.prueba.domain;

import java.util.UUID;

public class Car {
    private UUID carId;
    private boolean encendido;
    private String modelo;
    private Integer numPasajero;

    public Car() {
    }

    public Car(UUID carId, boolean encendido, String modelo, Integer numPasajero) {
        this.carId = carId;
        this.encendido = encendido;
        this.modelo = modelo;
        this.numPasajero = numPasajero;
    }

    public UUID getCarId() {
        return carId;
    }

    public void setCarId(UUID carId) {
        this.carId = carId;
    }

    public boolean isEncendido() {
        return encendido;
    }

    public void setEncendido(boolean encendido) {
        this.encendido = encendido;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getNumPasajero() {
        return numPasajero;
    }

    public void setNumPasajero(Integer numPasajero) {
        this.numPasajero = numPasajero;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", encendido=" + encendido +
                ", modelo='" + modelo + '\'' +
                ", numPasajero=" + numPasajero +
                '}';
    }
}
