package com.facturacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class FacturaService {
    
    private static final Logger logger = LoggerFactory.getLogger(FacturaService.class);
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Value("${api.url}")
    private String apiUrl;
    
    public String procesarFactura(JsonNode request) {
        try {
            logger.info("Procesando factura para NIT: {}", request.get("Nit").asText());
            
            // Crear el objeto de respuesta con el formato correcto
            ObjectNode responseNode = objectMapper.createObjectNode();
            
            // Agregar los campos de autenticación y configuración
            responseNode.put("Usuario", request.get("Usuario").asText());
            responseNode.put("Password", request.get("Password").asText());
            responseNode.put("Ambiente", request.get("Ambiente").asText());
            responseNode.put("Nit", request.get("Nit").asText());
            responseNode.put("PasswordPrivado", request.get("PasswordPrivado").asText());
            responseNode.put("TipoDte", request.get("TipoDte").asText());
            responseNode.put("CodigoGeneracion", request.get("CodigoGeneracion").asText());
            responseNode.put("NumControl", request.get("NumControl").asText());
            responseNode.put("VersionDte", request.get("VersionDte").asInt());
            
            // Crear un nuevo objeto para DteJson
            ObjectNode dteJsonNode = objectMapper.createObjectNode();
            dteJsonNode.set("identificacion", request.get("identificacion"));
            dteJsonNode.set("emisor", request.get("emisor"));
            dteJsonNode.set("receptor", request.get("receptor"));
            dteJsonNode.set("cuerpoDocumento", request.get("cuerpoDocumento"));
            dteJsonNode.set("resumen", request.get("resumen"));
            dteJsonNode.set("extension", request.get("extension"));
            dteJsonNode.set("apendice", request.get("apendice"));
            dteJsonNode.set("documentoRelacionado", request.get("documentoRelacionado"));
            dteJsonNode.set("otrosDocumentos", request.get("otrosDocumentos"));
            dteJsonNode.set("ventaTercero", request.get("ventaTercero"));
            
            // Agregar DteJson como string
            responseNode.put("DteJson", dteJsonNode.toString());
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            String requestBody = responseNode.toString();
            logger.info("JSON a enviar: {}", requestBody);
            
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
            
            String response = restTemplate.postForObject(apiUrl, entity, String.class);
            logger.info("Respuesta del API: {}", response);
            
            return response;
        } catch (Exception e) {
            logger.error("Error al procesar la factura: " + e.getMessage(), e);
            throw new RuntimeException("Error al procesar la factura: " + e.getMessage());
        }
    }
} 