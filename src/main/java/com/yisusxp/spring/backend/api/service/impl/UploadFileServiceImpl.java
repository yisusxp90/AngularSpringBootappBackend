package com.yisusxp.spring.backend.api.service.impl;

import com.yisusxp.spring.backend.api.service.IUploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

    public static final String UPLOADS = "uploads";
    private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
    @Override
    public Resource cargar(String nombreFoto) throws MalformedURLException {
        Path ruta = getPath(nombreFoto);
        log.info(ruta.toString());
        Resource recurso = new UrlResource(ruta.toUri());

        if(!recurso.exists() && !recurso.isReadable()){
            ruta = Paths.get("/src/main/resources/static/img").resolve("nouser.png").toAbsolutePath();
            recurso = new UrlResource(ruta.toUri());
            throw new RuntimeException("Error cargando la imagen" + nombreFoto);
        }
        return recurso;
    }

    @Override
    public String guardarImagem(MultipartFile archivo) throws IOException {
        String nombreArchivo = UUID.randomUUID().toString() + "_" +archivo.getOriginalFilename().replace(" ", " ");
        Path ruta = getPath(nombreArchivo);
        log.info(ruta.toString());
        Files.copy(archivo.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);
        return nombreArchivo;
    }

    @Override
    public boolean eliminar(String foto) {
        if (foto != null && foto.length() > 0) {
            Path rutaFotoAnterior = getPath(foto);
            File archivoFotoAnterior = rutaFotoAnterior.toFile();
            if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                archivoFotoAnterior.delete();
                return true;
            }
        }
        return false;
    }

    @Override
    public Path getPath(String nombreFoto) {
        return Paths.get(UPLOADS).resolve(nombreFoto).toAbsolutePath();
    }
}
