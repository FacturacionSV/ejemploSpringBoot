package com.facturacion.model;

import lombok.Data;

@Data
public class Extension {
    private String nombEntrega;
    private String docuEntrega;
    private Object nombRecibe;
    private Object docuRecibe;
    private Object observaciones;
    private Object placaVehiculo;
} 