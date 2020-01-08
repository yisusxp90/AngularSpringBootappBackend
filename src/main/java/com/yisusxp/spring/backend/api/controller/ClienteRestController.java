package com.yisusxp.spring.backend.api.controller;

import com.yisusxp.spring.backend.api.model.Cliente;
import com.yisusxp.spring.backend.api.model.Region;
import com.yisusxp.spring.backend.api.service.IClienteService;
import com.yisusxp.spring.backend.api.service.IRegionService;
import com.yisusxp.spring.backend.api.service.IUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.*;
import java.net.MalformedURLException;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {

    @Autowired
    IClienteService iClienteService;

    @Autowired
    IUploadFileService iUploadFileService;

    @Autowired
    IRegionService iRegionService;

    @GetMapping("/listar")
    public List<Cliente> listadoClientes() {
        return iClienteService.findAll();
    }

    @GetMapping("/clientes/filtrar-clientes/{termino}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Cliente> getClientes(@PathVariable String termino){
        return iClienteService.findByDniContainingIgnoreCase(termino);
    }


    @GetMapping("/listar/page/{page}")
    public Page<Cliente> listadoClientesPaginado(@PathVariable Integer page) {
        return iClienteService.findAll(PageRequest.of(page, 4));
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> buscarCliente(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        Cliente cliente;
        try {
            cliente = iClienteService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta");
            response.put("errors", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (cliente == null) {
            response.put("mensaje", "El cliente con ID: " + id + " no existe en la BD");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PostMapping("/clientes/crear")
    @ResponseStatus(HttpStatus.CREATED)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> crearCliente(@Valid @RequestBody Cliente cliente, BindingResult result) {
        Cliente cl;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            cl = iClienteService.save(cliente);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar cliente");
            response.put("errors", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("cliente", cl);
        response.put("mensaje", "Cliente creado exitosamente.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/clientes/borrar/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> borrarCliente(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Cliente clienteBD = null;
        try {
            clienteBD = iClienteService.findById(id);
            if (clienteBD == null) {
                response.put("mensaje", "El cliente con ID: " + id + " no existe en la BD");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            String nombreFotoAnterior = clienteBD.getFoto();
            iUploadFileService.eliminar(nombreFotoAnterior);
            iClienteService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar cliente");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Cliente Eliminado exitosamente.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/clientes/actualizar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> actualizarCliente(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Cliente clienteBD;
        Cliente clienteActualizado;

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            clienteBD = iClienteService.findById(id);
            if (clienteBD == null) {
                response.put("mensaje", "El cliente con ID: " + id + " no existe en la BD");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            if(cliente.equals(clienteBD)){
                response.put("mensaje", "Error Actualizando cliente");
                response.put("errors", new ArrayList<>(Arrays.asList("No existen cambios en el Cliente")));
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            clienteBD.setDni(cliente.getDni());
            clienteBD.setNombre(cliente.getNombre());
            clienteBD.setApellido(cliente.getApellido());
            clienteBD.setEmail(cliente.getEmail());
            clienteBD.setCreateAt(cliente.getCreateAt());
            clienteBD.setRegion(cliente.getRegion());
            clienteActualizado = iClienteService.save(clienteBD);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error Actualizando cliente");
            response.put("errors", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("cliente", clienteActualizado);
        response.put("mensaje", "Cliente Actualizado exitosamente.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/clientes/upload")
    public ResponseEntity<?> subirArchivo(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        Cliente clienteBD = null;
        String nombreArchivo = null;
        try {
            clienteBD = iClienteService.findById(id);
            if (clienteBD == null) {
                response.put("mensaje", "El cliente con ID: " + id + " no existe en la BD");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            if (!archivo.isEmpty()) {
                String nombreFotoAnterior = clienteBD.getFoto();
                iUploadFileService.eliminar(nombreFotoAnterior);
                nombreArchivo = iUploadFileService.guardarImagem(archivo);
            }

        } catch (IOException e) {
            response.put("mensaje", "Error Al subir la imagen");
            response.put("errors", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        clienteBD.setFoto(nombreArchivo);
        iClienteService.save(clienteBD);
        response.put("cliente", clienteBD);
        response.put("mensaje", "Imagen subida exitosamente.");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
        Resource recurso = null;
        try {
            recurso = iUploadFileService.cargar(nombreFoto);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "Attachment; filename=\""+ recurso.getFilename() + "\"");
        return new ResponseEntity<>(recurso, cabecera, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/clientes/regiones")
    public List<Region> listarRegiones(){
        return iRegionService.findAllRegiones();
    }
}
