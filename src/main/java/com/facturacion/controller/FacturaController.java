package com.facturacion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.facturacion.util.EnviadorCorreos;

@Controller
@RequestMapping("/factura")
public class FacturaController {
    
    private static final Logger logger = LoggerFactory.getLogger(FacturaController.class);
    
    @Autowired
    private EnviadorCorreos enviadorCorreos;
    
    @GetMapping("/nueva")
    public String nuevaFactura() {
        return "factura/nueva";
    }
    
    @PostMapping("/enviarCorreo")
    @ResponseBody
    public ResponseEntity<String> enviarCorreo(
            @RequestParam("pdfFactura") MultipartFile pdfFactura,
            @RequestParam("jsonFactura") String jsonFactura,
            @RequestParam("correoDestinatario") String correoDestinatario) {
        try {
            logger.info("Recibida solicitud para enviar correo a: {}", correoDestinatario);
            logger.info("Tamaño del PDF: {} bytes", pdfFactura != null ? pdfFactura.getSize() : 0);
            logger.info("Tamaño del JSON: {} bytes", jsonFactura != null ? jsonFactura.length() : 0);
            
            enviadorCorreos.enviarFacturaElectronica(
                pdfFactura != null ? pdfFactura.getBytes() : null,
                jsonFactura,
                correoDestinatario
            );
            
            logger.info("Correo enviado exitosamente");
            return ResponseEntity.ok("Correo enviado exitosamente");
        } catch (Exception e) {
            logger.error("Error al enviar correo: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body("Error al enviar el correo: " + e.getMessage());
        }
    }
} 