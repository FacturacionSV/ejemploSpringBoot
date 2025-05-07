package com.facturacion.service;

import com.facturacion.model.RequestUnificado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FacturaService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.url}")
    private String apiUrl;

    public String procesarFactura(RequestUnificado request) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<RequestUnificado> entity = new HttpEntity<>(request, headers);
            
            String response = restTemplate.postForObject(apiUrl, entity, String.class);
            
            if (response == null || response.isEmpty()) {
                throw new RuntimeException("No se recibió respuesta del servidor");
            }
            
            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar la factura: " + e.getMessage());
        }
    }
} 