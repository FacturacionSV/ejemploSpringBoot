package com.facturacion.model;

import lombok.Data;

@Data
public class Receptor {
    private String tipoDocumento;
    private String numDocumento;
    private Object nrc;
    private String nombre;
    private Object codActividad;
    private Object descActividad;
    private Direccion direccion;
    private String telefono;
    private String correo;
} 