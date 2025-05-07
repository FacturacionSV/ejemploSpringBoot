package com.keyjo.facturacion.model;

import lombok.Data;

@Data
public class Producto {
    private String codigo;
    private String descripcion;
    private double precioUni;
    private int cantidad;
    private double montoDescu;
    private double ventaGravada;
    private double ivaItem;
} 