package com.facturacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/factura")
public class FacturaController {

    @GetMapping("/nueva")
    public String nuevaFactura() {
        return "factura/nueva";
    }
} 