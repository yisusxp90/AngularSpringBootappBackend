package com.yisusxp.spring.backend.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.yisusxp.spring.backend.api.model.Usuario;
import com.yisusxp.spring.backend.api.service.IUploadFileService;
import com.yisusxp.spring.backend.api.service.IUsuarioService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class UsuarioRestController {

    @Autowired
    IUsuarioService iUsuarioService;

    @Autowired
    IUploadFileService iUploadFileService;


    @GetMapping("/usuarios/listar")
    public List<Usuario> listadoUsuarios() {
        return iUsuarioService.findAll();
    }
	/*
	 * @GetMapping("/usuarios/filtrar/{termino}")
	 * 
	 * @ResponseStatus(code = HttpStatus.OK) public List<Usuario>
	 * getUsuarios(@PathVariable String termino){ return
	 * iUsuarioService.findByDniContainingIgnoreCase(termino); }
	 */

    @GetMapping("/usuarios/page/{page}")
    public Page<Usuario> listadoUsuariosPaginado(@PathVariable Integer page) {
        return iUsuarioService.findAll(PageRequest.of(page, 5, Sort.by("enabled").ascending()));
    }

    @GetMapping("/usuario/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> buscarUsuario(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Usuario usuario;
        try {
            usuario = iUsuarioService.findById(id);
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

    @PostMapping("/usuario/crear")
    @ResponseStatus(HttpStatus.CREATED)  
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario usuario, BindingResult result) {
        Usuario user;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            user = iUsuarioService.save(usuario);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar usuario");
            response.put("errors", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("usuario", user);
        response.put("mensaje", "Usuario creado exitosamente.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/usuario/borrar/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> borrarUsuario(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Usuario usuarioBD = null;
        try {
            usuarioBD = iUsuarioService.findById(id);
            if (usuarioBD == null) {
                response.put("mensaje", "El usuario con ID: " + id + " no existe en la BD");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
     
            iUsuarioService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar usuario");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Usuario Eliminado exitosamente.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/usuario/actualizar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> actualizarUsuario(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Usuario usuarioBD;
        Usuario usuarioActualizado;

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            usuarioBD = iUsuarioService.findById(id);
            if (usuarioBD == null) {
                response.put("mensaje", "El usuario con ID: " + id + " no existe en la BD");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
            if(usuario.equals(usuarioBD)){
                response.put("mensaje", "Error Actualizando Usuario");
                response.put("errors", new ArrayList<>(Arrays.asList("No existen cambios en el Usuario")));
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            usuarioBD.setNombre(usuario.getNombre());
            usuarioBD.setApellido(usuario.getApellido());
            usuarioBD.setEmail(usuario.getEmail());
            usuarioBD.setEnabled(usuario.getEnabled()); // TODO disable if not user changes it
            usuarioBD.setPassword(iUsuarioService.encode(usuario.getPassword()));
            usuarioActualizado = iUsuarioService.save(usuarioBD);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error Actualizando Usuario");
            response.put("errors", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("usuario", usuarioActualizado);
        response.put("mensaje", "Usuario Actualizado exitosamente.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

	/*
	 * @Secured({"ROLE_ADMIN", "ROLE_USER"})
	 * 
	 * @PostMapping("/usuario/upload") public ResponseEntity<?>
	 * subirArchivo(@RequestParam("archivo") MultipartFile
	 * archivo, @RequestParam("id") Long id) { Map<String, Object> response = new
	 * HashMap<>(); Usuario clienteBD = null; String nombreArchivo = null; try {
	 * clienteBD = iUsuarioService.findById(id); if (clienteBD == null) {
	 * response.put("mensaje", "El cliente con ID: " + id + " no existe en la BD");
	 * return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); } if
	 * (!archivo.isEmpty()) { String nombreFotoAnterior = clienteBD.getFoto();
	 * iUploadFileService.eliminar(nombreFotoAnterior); nombreArchivo =
	 * iUploadFileService.guardarImagem(archivo); }
	 * 
	 * } catch (IOException e) { response.put("mensaje",
	 * "Error Al subir la imagen"); response.put("errors", e.getMessage()); return
	 * new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * clienteBD.setFoto(nombreArchivo); iUsuarioService.save(clienteBD);
	 * response.put("cliente", clienteBD); response.put("mensaje",
	 * "Imagen subida exitosamente.");
	 * 
	 * return new ResponseEntity<>(response, HttpStatus.CREATED); }
	 * 
	 * @GetMapping("/uploads/img/{nombreFoto:.+}") public ResponseEntity<Resource>
	 * verFoto(@PathVariable String nombreFoto){ Resource recurso = null; try {
	 * recurso = iUploadFileService.cargar(nombreFoto); } catch
	 * (MalformedURLException e) { e.printStackTrace(); }
	 * 
	 * HttpHeaders cabecera = new HttpHeaders();
	 * cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "Attachment; filename=\""+
	 * recurso.getFilename() + "\""); return new ResponseEntity<>(recurso, cabecera,
	 * HttpStatus.OK); }
	 * 
	 * @Secured({"ROLE_ADMIN"})
	 * 
	 * @GetMapping("/clientes/regiones") public List<Region> listarRegiones(){
	 * return iRegionService.findAllRegiones(); }
	 */
}
