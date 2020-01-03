package com.yisusxp.spring.backend.api.controller;

import com.yisusxp.spring.backend.api.model.Factura;
import com.yisusxp.spring.backend.api.model.Producto;
import com.yisusxp.spring.backend.api.service.IFacturaService;
import com.yisusxp.spring.backend.api.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class FacturaRestController {

    @Autowired
    private IFacturaService iFacturaService;

    @Autowired
    private IProductoService iProductoService;

    @GetMapping("/facturas/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Factura show(@PathVariable Long id){
        return iFacturaService.findById(id);
    }

    @DeleteMapping("/facturas/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        iFacturaService.delete(id);
    }

    @GetMapping("/facturas/filtrar-productos/{termino}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Producto> getProductos(@PathVariable String termino){
        return iProductoService.findByNombreContainingIgnoreCase(termino);
    }
}
