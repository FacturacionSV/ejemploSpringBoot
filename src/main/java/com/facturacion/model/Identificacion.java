package com.facturacion.model;

import lombok.Data;

@Data
public class Identificacion {
    private Integer version;
    private String ambiente;
    private String tipoDte;
    private String numeroControl;
    private String codigoGeneracion;
    private Integer tipoModelo;
    private Integer tipoOperacion;
    private Object tipoContingencia;
    private Object motivoContin;
    private String fecEmi;
    private String horEmi;
    private String tipoMoneda;
} 