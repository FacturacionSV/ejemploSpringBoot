package com.keyjo.facturacion.model;

import lombok.Data;
import java.util.List;

@Data
public class Factura {
    private Identificacion identificacion;
    private Emisor emisor;
    private Receptor receptor;
    private List<Producto> cuerpoDocumento;
    private Resumen resumen;
}

@Data
class Identificacion {
    private int version;
    private String ambiente;
    private String tipoDte;
    private String numeroControl;
    private String codigoGeneracion;
    private int tipoModelo;
    private int tipoOperacion;
    private String fecEmi;
    private String horEmi;
    private String tipoMoneda;
}

@Data
class Emisor {
    private String nit;
    private String nrc;
    private String nombre;
    private String codActividad;
    private String descActividad;
    private String nombreComercial;
    private String tipoEstablecimiento;
    private Direccion direccion;
    private String telefono;
    private String correo;
}

@Data
class Receptor {
    private String tipoDocumento;
    private String numDocumento;
    private String nombre;
    private Direccion direccion;
    private String telefono;
    private String correo;
}

@Data
class Direccion {
    private String departamento;
    private String municipio;
    private String complemento;
}

@Data
class Resumen {
    private double totalGravada;
    private double subTotalVentas;
    private double totalDescu;
    private double subTotal;
    private double montoTotalOperacion;
    private double totalPagar;
    private double totalIva;
} 