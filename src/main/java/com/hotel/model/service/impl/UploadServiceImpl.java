package com.hotel.model.service.impl;

import com.hotel.model.service.UploadService;
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
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {
    
    private static final String UPLOADS          = "uploads";
    private static final String DEFAULT_JPG      = "default.jpg";
    private static final String RESOURCES_STATIC = "src/main/resources/static";
    
    @Override
    public Resource cargar(String nombreFoto) throws MalformedURLException {
        Path     rutaArchivo = getpath(nombreFoto);
        Resource recurso;
        recurso = new UrlResource(rutaArchivo.toUri());
        if (!recurso.exists() && !recurso.isReadable()) {
            rutaArchivo = Paths.get(RESOURCES_STATIC).resolve(DEFAULT_JPG).toAbsolutePath();
            recurso     = new UrlResource(rutaArchivo.toUri());
        }
        return recurso;
    }
    
    @Override
    public String copiar(MultipartFile archivo) throws IOException {
        String nombreArchivo;
        Path   rutaArchivo;
        if (archivo.isEmpty()){
           return "Archivo vacio";
        }
        nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
        rutaArchivo = getpath(nombreArchivo);
        Files.copy(archivo.getInputStream(), rutaArchivo);
        return nombreArchivo;
    }
    
    @Override
    public boolean eliminar(String nombreFoto) {
        if (nombreFoto != null && nombreFoto.length() > 0) {
            Path rutaFotoAnterior    = Paths.get(UPLOADS).resolve(nombreFoto).toAbsolutePath();
            File archivoFotoAnterior = rutaFotoAnterior.toFile();
            if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                archivoFotoAnterior.delete();
                return true;
            }
            
        }
        return false;
    }
    
    @Override
    public Path getpath(String nombreFoto) {
        return Paths.get(UPLOADS).resolve(nombreFoto).toAbsolutePath();
    }
}
