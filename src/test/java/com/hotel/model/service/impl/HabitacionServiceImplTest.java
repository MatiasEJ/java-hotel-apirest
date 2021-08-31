package com.hotel.model.service.impl;

import com.hotel.model.dao.HabitacionDao;
import com.hotel.model.entity.Habitacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;


class HabitacionServiceImplTest {
    
    @InjectMocks
    HabitacionServiceImpl habitacionService;
    
    @Mock
    HabitacionDao habitacionDao;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void findHabitacionById() {
        Habitacion habitacion = new Habitacion();
        habitacion.setNum_id("123123");
        habitacion.setPrecio_noche(9000);
        
        when(habitacionDao.findById(anyLong())).thenReturn(Optional.of(habitacion));
        
        Habitacion resultHab = habitacionService.findHabitacionById(anyLong());
    
        Assertions.assertNotNull(resultHab);
        
    }
    
    @Test
    void findAll() {
        Habitacion habitacion = new Habitacion();
        habitacion.setNum_id("123123");
        habitacion.setPrecio_noche(9000);
        
        List<Habitacion> listaHabitaciones = new ArrayList<>();
        listaHabitaciones.add(habitacion);
        when(habitacionDao.findAll()).thenReturn(listaHabitaciones);
        
        List<Habitacion> listaHabs = habitacionService.findAll();
        Assertions.assertEquals(listaHabs.size(),1 );
        
        
    }
}