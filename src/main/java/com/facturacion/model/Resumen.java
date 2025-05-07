package com.facturacion.model;

import java.util.List;
import lombok.Data;

@Data
public class Resumen {
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