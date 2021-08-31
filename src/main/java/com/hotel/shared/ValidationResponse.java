package com.hotel.shared;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ValidationResponse {
    
    public static ResponseEntity<?> handleValidationResponse(BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        List<String> errors = result.getFieldErrors().stream()
                                    .map(error -> "El campo: " + error.getField() + " " + error.getDefaultMessage())
                                    .collect(Collectors.toList());
        
        response.put("errors", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    public static ResponseEntity<?> updateSuccessMsg(Object obj) {
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Datos actualizados.");
        response.put("habitacion", obj);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    public static ResponseEntity<?> notFoundInDB(Map<String, Object> response) {
        response.put("mensaje", "No se encuentra en la DB.");
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }
    
    public static ResponseEntity<?> errorConsulta(Map<String, Object> response, DataAccessException ex) {
        response.put("mensaje", "Error al realizar la consulta: ".concat(ex.getMostSpecificCause().getMessage()));
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    public static ResponseEntity<?> creadoSuccess(Map<String, Object> response) {
        response.put("mensaje", "Creado con exito.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
