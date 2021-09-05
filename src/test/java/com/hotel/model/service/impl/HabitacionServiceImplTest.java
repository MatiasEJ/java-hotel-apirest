package com.hotel.model.service.impl;

import com.hotel.model.dao.HabitacionDao;
import com.hotel.model.entity.Habitacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


class HabitacionServiceImplTest {
    
    private static final double PRECIO_NOCHE = 9000;
    private static final String NUM_ID       = "123123";
    private static final Long   ID           = Integer.toUnsignedLong(3);
    
    @InjectMocks
    HabitacionServiceImpl habitacionService;
    
    @Mock
    HabitacionDao habitacionDao;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void findAll() {
        List<Habitacion> listaHabitaciones = new ArrayList<>();
        listaHabitaciones.add(new Habitacion(PRECIO_NOCHE, NUM_ID));
        when(habitacionDao.findAll()).thenReturn(listaHabitaciones);
        List<Habitacion> listaHabs = habitacionService.findAll();
        Assertions.assertEquals(listaHabs.size(), 1);
    }
    
    @Test
    void save() {
        Habitacion habitacion = new Habitacion(PRECIO_NOCHE, NUM_ID);
        when(habitacionDao.save(habitacion)).thenReturn(habitacion);
        
        Habitacion nuevaHab = habitacionService.save(habitacion);
        verify(habitacionDao, times(1)).save(habitacion);
        assertNotNull(nuevaHab);
    }
    
    @Test
    void testFindHabitacionById() {
        Habitacion habitacion = new Habitacion(ID, PRECIO_NOCHE);
        when(habitacionDao.findById(anyLong())).thenReturn(Optional.of(habitacion));
        Habitacion hab = habitacionService.findHabitacionById(habitacion.getId());
        assertNotNull(hab);
        assertEquals(PRECIO_NOCHE,hab.getPrecio_noche());
        verify(habitacionDao, times(1)).findById(hab.getId());
    }
    
    
    
    
}