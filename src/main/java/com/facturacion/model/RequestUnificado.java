package com.facturacion.model;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class RequestUnificado {
    // Campos de autenticación
    private String Usuario;
    private String Password;
    private String Ambiente;
    private String Nit;
    private String PasswordPrivado;
    private String TipoDte;
    private String CodigoGeneracion;
    private String NumControl;
    private Integer VersionDte;
    
    // Campos del DTE
    private Map<String, Object> identificacion;
    private Map<String, Object> emisor;
    private Map<String, Object> receptor;
    private List<Map<String, Object>> cuerpoDocumento;
    private Map<String, Object> resumen;
    private Map<String, Object> extension;
    private Object documentoRelacionado;
    private Object otrosDocumentos;
    private Object ventaTercero;
    private Object apendice;
} 