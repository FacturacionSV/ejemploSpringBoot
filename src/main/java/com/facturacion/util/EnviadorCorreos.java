package com.facturacion.util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

@Component
public class EnviadorCorreos {
    
    private static final Logger logger = LoggerFactory.getLogger(EnviadorCorreos.class);
    
    @Value("${mail.smtp.host}")
    private String smtpHost;
    
    @Value("${mail.smtp.port}")
    private int smtpPort;
    
    @Value("${mail.smtp.user}")
    private String smtpUser;
    
    @Value("${mail.smtp.password}")
    private String smtpPassword;
    
    @Value("${mail.smtp.ssl.enable}")
    private boolean smtpSslEnable;
    
    @Value("${mail.smtp.auth}")
    private boolean smtpAuth;
    
    public void enviarFacturaElectronica(byte[] pdfFactura, String jsonFactura, String correoDestinatario) {
        try {
            logger.info("Iniciando envío de correo a: {}", correoDestinatario);
            
            // Configurar propiedades del servidor SMTP
            Properties props = new Properties();
            props.put("mail.smtp.host", smtpHost);
            props.put("mail.smtp.port", smtpPort);
            props.put("mail.smtp.auth", String.valueOf(smtpAuth));
            props.put("mail.smtp.ssl.enable", String.valueOf(smtpSslEnable));
            props.put("mail.smtp.ssl.trust", smtpHost);
            props.put("mail.debug", "true");
            
            logger.info("Configurando sesión SMTP con host: {} y puerto: {}", smtpHost, smtpPort);
            
            // Crear sesión
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(smtpUser, smtpPassword);
                }
            });
            
            // Crear mensaje
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(smtpUser, "Sistema de Facturación Electrónica"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestinatario));
            message.setSubject("Documento Tributario Electrónico - Factura");
            
            // Crear cuerpo del mensaje
            String cuerpoMensaje = "Estimado cliente,\n\n" +
                    "Adjunto encontrará su factura electrónica en formato PDF y JSON.\n\n" +
                    "Este es un mensaje automático, por favor no responda.\n\n" +
                    "Atentamente,\n" +
                    "Sistema de Facturación Electrónica  springboot";
            
            // Crear multipart para adjuntos
            Multipart multipart = new MimeMultipart();
            
            // Agregar cuerpo del mensaje
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(cuerpoMensaje);
            multipart.addBodyPart(messageBodyPart);
            
            // Agregar PDF si existe
            if (pdfFactura != null && pdfFactura.length > 0) {
                logger.info("Agregando archivo PDF al correo");
                BodyPart pdfAttachment = new MimeBodyPart();
                pdfAttachment.setDataHandler(new DataHandler(new ByteArrayDataSource(pdfFactura, "application/pdf")));
                pdfAttachment.setFileName("FacturaElectronica.pdf");
                multipart.addBodyPart(pdfAttachment);
            } else {
                logger.warn("No se encontró archivo PDF para adjuntar");
            }
            
            // Agregar JSON
            logger.info("Agregando archivo JSON al correo");
            BodyPart jsonAttachment = new MimeBodyPart();
            jsonAttachment.setDataHandler(new DataHandler(new ByteArrayDataSource(jsonFactura.getBytes("UTF-8"), "application/json")));
            jsonAttachment.setFileName("FacturaElectronica.json");
            multipart.addBodyPart(jsonAttachment);
            
            // Establecer contenido del mensaje
            message.setContent(multipart);
            
            // Enviar mensaje
            logger.info("Intentando enviar correo...");
            Transport.send(message);
            
            logger.info("Correo enviado exitosamente a: {}", correoDestinatario);
            
        } catch (Exception e) {
            logger.error("Error detallado al enviar correo: {}", e.getMessage(), e);
            throw new RuntimeException("Error al enviar la factura por correo: " + e.getMessage(), e);
        }
    }
    
    // Clase auxiliar para manejar datos binarios
    private static class ByteArrayDataSource implements DataSource {
        private byte[] data;
        private String type;
        
        public ByteArrayDataSource(byte[] data, String type) {
            this.data = data;
            this.type = type;
        }
        
        @Override
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(data);
        }
        
        @Override
        public OutputStream getOutputStream() throws IOException {
            throw new IOException("No se puede escribir en esta fuente de datos");
        }
        
        @Override
        public String getContentType() {
            return type;
        }
        
        @Override
        public String getName() {
            return "ByteArrayDataSource";
        }
    }
} 