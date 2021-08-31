package com.hotel.controller;

import com.hotel.model.entity.Habitacion;
import com.hotel.model.service.impl.HabitacionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
