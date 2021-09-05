package com.hotel.controller;

import com.hotel.model.dao.HabitacionDao;
import com.hotel.model.entity.Habitacion;
import com.hotel.model.service.impl.HabitacionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class HabitacionRestControllerTest {
    
    private static final long HAB_ID       = Integer.toUnsignedLong(3);
    public static final int   PRECIO_NOCHE = 90;
    
    @InjectMocks
    HabitacionRestController habitacionRestController;
    
    @Mock
    HabitacionServiceImpl habitacionService;
    
    Habitacion hab_stub;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        hab_stub = new Habitacion();
        hab_stub.setId(HAB_ID);
        hab_stub.setPrecio_noche(PRECIO_NOCHE);
        
    }
    
    @Test
    void index() {
    }
    
    
    @Test
    void create() {
        when(habitacionService.save(any(Habitacion.class))).thenReturn(hab_stub);
        ResponseEntity<?> response = habitacionRestController.createHabitacion(Habitacion.builder().id(HAB_ID).build());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
    @Test
    void create_nullHabitacion(){
        when(habitacionService.save(any(Habitacion.class))).thenReturn(null);
        ResponseEntity<?> response = habitacionRestController.createHabitacion(null);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }
    @Test
    void create_DataAccessException(){
        when(habitacionService.save(any(Habitacion.class))).thenThrow(new DataAccessException("lo que") {
            @Override
            public Throwable getMostSpecificCause() {
                return super.getMostSpecificCause();
            }
        });
        ResponseEntity<?> response = habitacionRestController.createHabitacion(hab_stub);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
        
    }
    
    @Test
    void createError() {
    
    }
    
    @Test
    void testCreate() {
    }
    
    @Test
    void findHabitacionById_Success() {
        when(habitacionService.findHabitacionById(anyLong())).thenReturn(hab_stub);
        ResponseEntity<?> response = habitacionRestController.findHabitacionById(hab_stub.getId());
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
    
    @Test
    void findHabitacionById_DataAccessException() {
        
        when(habitacionService.findHabitacionById(anyLong())).thenThrow(new DataAccessException("lo que") {
            @Override
            public Throwable getMostSpecificCause() {
                return super.getMostSpecificCause();
            }
        });
        ResponseEntity<?> response = habitacionRestController.findHabitacionById(hab_stub.getId());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
    
    @Test
    void findHabitacionById_nullHabitacion(){
        when(habitacionService.findHabitacionById(anyLong())).thenReturn(null);
        ResponseEntity<?> response = habitacionRestController.findHabitacionById(hab_stub.getId());
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }
    
}