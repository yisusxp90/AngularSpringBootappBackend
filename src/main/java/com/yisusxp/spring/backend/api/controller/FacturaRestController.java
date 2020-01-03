package com.yisusxp.spring.backend.api.controller;

import com.yisusxp.spring.backend.api.model.Factura;
import com.yisusxp.spring.backend.api.model.Producto;
import com.yisusxp.spring.backend.api.service.IFacturaService;
import com.yisusxp.spring.backend.api.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @PostMapping("/facturas/crear")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> crearFactura(@Valid @RequestBody Factura factura, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            Factura fact = iFacturaService.save(factura);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al crear factura");
            response.put("errors", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Favtura creada exitosamente.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
