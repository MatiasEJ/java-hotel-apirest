package com.hotel.controller;

import com.hotel.model.entity.Habitacion;
import com.hotel.model.entity.Tematica;
import com.hotel.model.entity.TipoHabitacion;
import com.hotel.model.service.impl.HabitacionServiceImpl;
import com.hotel.shared.ValidationResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hotel.shared.ValidationResponse.*;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@NoArgsConstructor
@RequestMapping("/hab")
public class HabitacionRestController {
    @Autowired
    private HabitacionServiceImpl habitacionService;
    
    @GetMapping("/habitaciones")
    public List<Habitacion> index() {return habitacionService.findAll();}
    
    @GetMapping("/habitacion/{id}")
    public ResponseEntity<?> findHabitacionById(@PathVariable Long id) {
        Habitacion          habitacion;
        Map<String, Object> response = new HashMap<>();
        try {
            habitacion = habitacionService.findHabitacionById(id);
        } catch (DataAccessException ex) {
            return errorConsulta(response, ex);
        }
        
        if (habitacion == null) {
            return notFoundInDB(response);
        }
        
        return new ResponseEntity(habitacion, HttpStatus.OK);
    }
    
    
    @PostMapping("/habitacion")
    public ResponseEntity<?> createHabitacion(@RequestBody Habitacion habitacion) {
        Habitacion          newhabitacion;
        Map<String, Object> response = new HashMap<>();
        if(habitacion == null){
            response.put("mensaje","No existe valor de habitacion");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        try{
            newhabitacion = habitacionService.save(habitacion);
        }catch (DataAccessException ex){
            return errorConsulta(response, ex);
        }
    
        return creadoSuccess(response);
    }
    
    
    @PutMapping("/habitacion/{id}")
    public ResponseEntity<?> updateHabitacionById(@RequestBody Habitacion habitacion, @PathVariable Long id) {
        Habitacion habitacionActual         = habitacionService.findHabitacionById(id);
        Habitacion habActualizada = null;
        Map<String, Object> response = new HashMap<>();
        
        if(habitacionActual == null){
            return notFoundInDB(response);
        }
        
       try{
           habitacionActual.setNum_id(habitacion.getNum_id());
           habitacionActual.setTematica(habitacion.getTematica());
           habitacionActual.setPrecio_noche(habitacion.getPrecio_noche());
    
           habActualizada = habitacionService.save(habitacionActual);
       }catch (DataAccessException ex){
            return errorConsulta(response, ex);
       }
        return ValidationResponse.updateSuccessMsg(habActualizada);
    }
    
    
    @DeleteMapping("/habitacion/{id}")
    public void deleteHabitacionById(@PathVariable Long id) {
        habitacionService.delete(id);
    }
    
    
    @GetMapping("/habitaciones/tematicas")
    public List<Tematica> findAllTematicas(){
        return habitacionService.findAllTematicas();
    }
    @GetMapping("/habitaciones/tiposHabitacion")
    public List<TipoHabitacion> findAllTipoHabitacion(){
        return habitacionService.findAllTipoHabitacion();
    }
}
