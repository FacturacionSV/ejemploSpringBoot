package com.facturacion.model;

import lombok.Data;

@Data
public class Pago {
    private String codigo;
    private Double montoPago;
    private String referencia;
    private Object periodo;
    private Object plazo;
} 