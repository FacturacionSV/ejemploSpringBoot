package com.facturacion.model;

import lombok.Data;

@Data
public class Emisor {
    private String nit;
    private String nrc;
    private String nombre;
    private String codActividad;
    private String descActividad;
    private String nombreComercial;
    private String tipoEstablecimiento;
    private Direccion direccion;
    private String telefono;
    private Object codEstableMH;
    private Object codEstable;
    private Object codPuntoVentaMH;
    private Object codPuntoVenta;
    private String correo;
} 