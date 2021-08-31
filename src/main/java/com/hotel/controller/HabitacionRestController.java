package com.hotel.controller;

import com.hotel.model.entity.Habitacion;
import com.hotel.model.service.impl.HabitacionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hab")
public class HabitacionRestController {
    @Autowired
    private HabitacionServiceImpl habitacionService;
    
    @GetMapping("/habitaciones")
    public List<Habitacion> index(){return habitacionService.findAll();}
    
    @GetMapping("/habitacion/{id}")
    public Habitacion find(@PathVariable Long id){
        return habitacionService.find(id);
    }
    
    @PostMapping("/habitacion")
    public void create(@RequestBody Habitacion habitacion){
        habitacionService.save(habitacion);
    }
    
    @PutMapping("/habitacion/{id}")
    public Habitacion update(@RequestBody Habitacion habitacion,@PathVariable Long id){
        Habitacion actual = habitacionService.find(id);
        Habitacion habActualizada = null;
        
        actual.setNum_id(habitacion.getNum_id());
        actual.setTematica(habitacion.getTematica());
        actual.setTipo_hab(habitacion.getTipo_hab());
        actual.setPrecio_noche(habitacion.getPrecio_noche());
        
        habActualizada = habitacionService.save(actual) ;
        return habActualizada;
    }
    
    @DeleteMapping("/habitacion/{id}")
    public void delete(@PathVariable Long id){
        habitacionService.delete(id);
    }
    
    
}
