package com.hotel.controller;

import com.hotel.model.entity.Habitacion;
import com.hotel.model.service.impl.HabitacionServiceImpl;
import com.hotel.shared.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hotel.shared.ValidationResponse.*;

@RestController
@RequestMapping("/hab")
public class HabitacionRestController {
    @Autowired
    private HabitacionServiceImpl habitacionService;
    
    @GetMapping("/habitaciones")
    public List<Habitacion> index() {return habitacionService.findAll();}
    
    @GetMapping("/habitacion/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        Habitacion          habitacion;
        Map<String, Object> response = new HashMap<>();
        try {
            habitacion = habitacionService.find(id);
        } catch (DataAccessException ex) {
            return errorConsulta(response, ex);
        }
        
        if (habitacion == null) {
            return notFoundInDB(response);
        }
        
        return new ResponseEntity(habitacion, HttpStatus.OK);
    }
    
    
    @PostMapping("/habitacion")
    public ResponseEntity<?> create(@RequestBody Habitacion habitacion) {
        Habitacion          newhabitacion;
        Map<String, Object> response = new HashMap<>();
        
        try{
            newhabitacion = habitacionService.save(habitacion);
        }catch (DataAccessException ex){
            return errorConsulta(response, ex);
        }
    
        return creadoSuccess(response);
    }
    
    
    @PutMapping("/habitacion/{id}")
    public ResponseEntity<?> update(@RequestBody Habitacion habitacion, @PathVariable Long id) {
        Habitacion habitacionActual         = habitacionService.find(id);
        Habitacion habActualizada = null;
        Map<String, Object> response = new HashMap<>();
        
        if(habitacionActual == null){
            return notFoundInDB(response);
        }
        
       try{
           habitacionActual.setNum_id(habitacion.getNum_id());
           habitacionActual.setTematica(habitacion.getTematica());
           habitacionActual.setTipo_hab(habitacion.getTipo_hab());
           habitacionActual.setPrecio_noche(habitacion.getPrecio_noche());
    
           habActualizada = habitacionService.save(habitacionActual);
       }catch (DataAccessException ex){
            return errorConsulta(response, ex);
       }
        return ValidationResponse.updateSuccessMsg(habActualizada);
    }
    
    
    @DeleteMapping("/habitacion/{id}")
    public void delete(@PathVariable Long id) {
        habitacionService.delete(id);
    }
    
    
}
