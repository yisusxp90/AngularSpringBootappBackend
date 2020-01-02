package com.yisusxp.spring.backend.api.controller;

import com.yisusxp.spring.backend.api.model.Factura;
import com.yisusxp.spring.backend.api.service.IFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class FacturaRestController {

    @Autowired
    private IFacturaService iFacturaService;

    @GetMapping("/facturas/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Factura show(@PathVariable Long id){
        return iFacturaService.findById(id);
    }
}
