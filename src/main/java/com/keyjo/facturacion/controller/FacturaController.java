package com.keyjo.facturacion.controller;

import com.keyjo.facturacion.model.Factura;
import com.keyjo.facturacion.model.Producto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/factura")
public class FacturaController {

    @GetMapping("/nueva")
    public String nuevaFactura(Model model) {
        model.addAttribute("factura", new Factura());
        model.addAttribute("producto", new Producto());
        model.addAttribute("productos", new ArrayList<Producto>());
        return "factura/nueva";
    }

    @PostMapping("/agregar-producto")
    @ResponseBody
    public List<Producto> agregarProducto(@RequestBody Producto producto) {
        // Aquí iría la lógica para agregar el producto a la lista
        return new ArrayList<>();
    }

    @PostMapping("/procesar")
    @ResponseBody
    public String procesarFactura(@RequestBody Factura factura) {
        // Aquí iría la lógica para enviar la factura a la API externa
        return "Factura procesada correctamente";
    }
} 