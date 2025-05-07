package com.facturacion.model;

import lombok.Data;
import java.util.List;

@Data
public class RequestUnificado {
    // Propiedades del DteJson
    private Identificacion identificacion;
    private Object documentoRelacionado;
    private Emisor emisor;
    private Receptor receptor;
    private Object ventaTercero;
    private List<CuerpoDocumento> cuerpoDocumento;
    private Resumen resumen;
    private Extension extension;
    private Object otrosDocumentos;
    private Object apendice;

    // Propiedades adicionales del request
    private String Usuario;
    private String Password;
    private String Ambiente;
    private String Nit;
    private String PasswordPrivado;
    private String TipoDte;
    private String CodigoGeneracion;
    private String NumControl;
    private Integer VersionDte;
}

@Data
class Identificacion {
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
    private Object codEstableMH;
    private Object codEstable;
    private Object codPuntoVentaMH;
    private Object codPuntoVenta;
    private String correo;
}

@Data
class Receptor {
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

@Data
class Direccion {
    private String departamento;
    private String municipio;
    private String complemento;
}

@Data
class CuerpoDocumento {
    private Integer numItem;
    private Integer tipoItem;
    private Object numeroDocumento;
    private Double cantidad;
    private String codigo;
    private Object codTributo;
    private Integer uniMedida;
    private String descripcion;
    private Double precioUni;
    private Double montoDescu;
    private Double ventaNoSuj;
    private Double ventaExenta;
    private Double ventaGravada;
    private Object tributos;
    private Double psv;
    private Double noGravado;
    private Double ivaItem;
}

@Data
class Resumen {
    private Double totalNoSuj;
    private Double totalExenta;
    private Double totalGravada;
    private Double subTotalVentas;
    private Double descuNoSuj;
    private Double descuExenta;
    private Double descuGravada;
    private Double porcentajeDescuento;
    private Double totalDescu;
    private Object tributos;
    private Double subTotal;
    private Double ivaRete1;
    private Double reteRenta;
    private Double montoTotalOperacion;
    private Double totalNoGravado;
    private Double totalPagar;
    private String totalLetras;
    private Double totalIva;
    private Double saldoFavor;
    private Integer condicionOperacion;
    private List<Pago> pagos;
    private String numPagoElectronico;
}

@Data
class Pago {
    private String codigo;
    private Double montoPago;
    private String referencia;
    private Object periodo;
    private Object plazo;
}

@Data
class Extension {
    private String nombEntrega;
    private String docuEntrega;
    private Object nombRecibe;
    private Object docuRecibe;
    private Object observaciones;
    private Object placaVehiculo;
} 