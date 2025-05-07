package com.facturacion.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.facturacion.util.EnviadorCorreos;

@Service
public class FacturaService {
    
    private static final Logger logger = LoggerFactory.getLogger(FacturaService.class);
    
    @Value("${api.url}")
    private String apiUrl;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private EnviadorCorreos enviadorCorreos;
    
    public String procesarFactura(JsonNode request) {
        try {
            logger.info("Procesando factura para NIT: {}", request.get("Nit").asText());
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);
            
            String response = restTemplate.postForObject(apiUrl, entity, String.class);
            logger.info("Respuesta de la API recibida: {}", response);
            
            // Obtener el correo del receptor del JSON
            String correoDestinatario = request.get("DteJson").get("receptor").get("correo").asText();
            logger.info("Enviando correo a: {}", correoDestinatario);
            
            try {
                // Enviar correo con la factura
                enviadorCorreos.enviarFacturaElectronica(
                    null, // Aquí deberías obtener el PDF de la respuesta
                    request.toString(),
                    correoDestinatario
                );
                logger.info("Correo enviado exitosamente");
            } catch (Exception e) {
                logger.error("Error al enviar el correo: {}", e.getMessage(), e);
                // No lanzamos la excepción para no interrumpir el flujo principal
            }
            
            return response;
        } catch (Exception e) {
            logger.error("Error al procesar la factura: {}", e.getMessage(), e);
            throw new RuntimeException("Error al procesar la factura", e);
        }
    }
} 