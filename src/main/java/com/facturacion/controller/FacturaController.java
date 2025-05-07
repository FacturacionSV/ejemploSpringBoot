package com.facturacion.controller;

import com.facturacion.model.RequestUnificado;
import com.facturacion.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping("/procesar-java")
    public ResponseEntity<?> procesarFacturaJava(@RequestBody RequestUnificado request) {
        try {
            String selloRecibido = facturaService.procesarFactura(request);
            return ResponseEntity.ok().body(selloRecibido);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al procesar la factura: " + e.getMessage());
        }
    }
} 