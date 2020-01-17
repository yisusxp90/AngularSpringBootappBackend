package com.yisusxp.spring.backend.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yisusxp.spring.backend.api.model.Role;
import com.yisusxp.spring.backend.api.service.IRolService;
import com.yisusxp.spring.backend.api.service.IUploadFileService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class RoleRestController {

    @Autowired
    IRolService iRolService;

    @Autowired
    IUploadFileService iUploadFileService;


    @GetMapping("/roles")
    public List<Role> roles() {
        return iRolService.findAll();
    }
	/*
	 * @GetMapping("/usuarios/filtrar/{termino}")
	 * 
	 * @ResponseStatus(code = HttpStatus.OK) public List<Usuario>
	 * getUsuarios(@PathVariable String termino){ return
	 * iUsuarioService.findByDniContainingIgnoreCase(termino); }
	 */

    @GetMapping("/rol/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> buscarRol(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Role usuario;
        try {
            usuario = iRolService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta");
            response.put("errors", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (usuario == null) {
            response.put("mensaje", "El usuario con ID: " + id + " no existe en la BD");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

}
