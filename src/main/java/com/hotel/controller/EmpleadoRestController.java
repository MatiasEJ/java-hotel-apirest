package com.hotel.controller;

import com.hotel.model.entity.Empleado;
import com.hotel.model.entity.Tematica;
import com.hotel.model.service.impl.EmpleadoServiceImpl;
import com.hotel.model.service.impl.UploadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hotel.shared.ValidationResponse.*;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/api")
public class EmpleadoRestController {
    
    @Autowired
    private EmpleadoServiceImpl empleadoService;
    @Autowired
    private UploadServiceImpl   uploadService;
    
    public EmpleadoRestController(EmpleadoServiceImpl empleadoService, UploadServiceImpl uploadService) {
        this.empleadoService = empleadoService;
        this.uploadService   = uploadService;
    }
    
    @GetMapping(EmpleadoUri.EMPLEADOS)
    public List<Empleado> index() {
        return empleadoService.findAll();
    }
    
    @GetMapping("/empleados/page/{page}")
    public Page<Empleado> index(@PathVariable Integer page) {
        return empleadoService.findAll(PageRequest.of(page, 5));
    }
    
    @GetMapping(EmpleadoUri.EMPLEADO_ID)
    public ResponseEntity<?> findEmpleadoById(@PathVariable Long id) {
        Empleado            empleado;
        Map<String, Object> response = new HashMap<>();
        try {
            empleado = empleadoService.findById(id);
        } catch (DataAccessException ex) {
            response.put("mensaje", "Error al realizar la consulta"
                .concat(": ")
                .concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (empleado == null) {
            return notFoundInDB(response);
        }
        return new ResponseEntity<>(empleado, HttpStatus.OK);
    }
    
    
    @PutMapping(EmpleadoUri.EMPLEADO_ID)
    public ResponseEntity<?> updateById(@Valid @RequestBody Empleado empleado, BindingResult result, @PathVariable Long id) {
        Map<String, Object> response            = new HashMap<>();
        Empleado            empleadoActual      = empleadoService.findById(id);
        Empleado            empleadoActualizado = null;
        
        if (result.hasErrors()) {
            return handleValidationResponse(result);
        }
        
        if (empleadoActual == null) {
            return notFoundInDB(response);
        }
        
        try {
            empleadoActual.setNombre(empleado.getNombre());
            empleadoActual.setApellido(empleado.getApellido());
            empleadoActual.setDni(empleado.getDni());
            empleadoActual.setDireccion(empleado.getDireccion());
            empleadoActual.setFechaNacimiento(empleado.getFechaNacimiento());
            
            empleadoActualizado = empleadoService.save(empleadoActual);
            
        } catch (DataAccessException ex) {
            return errorConsulta(response, ex);
        }
        return updateSuccessMsg(empleadoActualizado);
    }
    
    
    @PostMapping(EmpleadoUri.EMPLEADO)
    public ResponseEntity<?> create(@Valid @RequestBody Empleado empleado, BindingResult result) {
        Empleado            nuevoEmpleado = null;
        Map<String, Object> response      = new HashMap<>();
        
        if (result.hasErrors()) {
            return handleValidationResponse(result);
        }
        
        try {
            nuevoEmpleado = empleadoService.save(empleado);
        } catch (DataAccessException ex) {
            response.put("mensaje", "Error al realizar INSERT"
                .concat(": ")
                .concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("mensaje", "Empleado creado con exito.");
        response.put("empleado", nuevoEmpleado);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    
    
    @DeleteMapping(EmpleadoUri.EMPLEADO_ID)
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Empleado empleado = empleadoService.findById(id);
            uploadService.eliminar(empleado.getFoto());
            empleadoService.deleteById(id);
        } catch (DataAccessException ex) {
            response.put("mensaje", "Error al realizar DELETE"
                .concat(": ")
                .concat(ex.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("mensaje", "Empleado borrado con exito.");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
    
    @PostMapping("/empleados/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        Empleado            empleado = null;
        
        try {
            empleado = empleadoService.findById(id);
        } catch (DataAccessException ex) {
            return errorConsulta(response, ex);
        }
        if (!archivo.isEmpty()) {
            String nombreArchivo;
            try {
                nombreArchivo = uploadService.copiar(archivo);
            } catch (IOException ex) {
                response.put("mensaje", "Error al subir imagen : ".concat(ex.getLocalizedMessage()));
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                
            }
            String nombreFotoAnterior = empleado.getFoto();
            uploadService.eliminar(nombreFotoAnterior);
            
            empleado.setFoto(nombreArchivo);
            empleadoService.save(empleado);
        }
        
        response.put("empleado", empleado);
        response.put("mensaje", "Imagen subida con exito.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) throws MalformedURLException {
        Resource recurso     = uploadService.cargar(nombreFoto);
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + recurso.getFilename() + "\"");
        return new ResponseEntity<>(recurso, cabecera, HttpStatus.OK);
    }
    
    
}
