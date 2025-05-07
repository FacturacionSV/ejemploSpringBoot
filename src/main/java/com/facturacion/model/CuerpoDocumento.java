package com.facturacion.model;

import lombok.Data;

@Data
public class CuerpoDocumento {
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