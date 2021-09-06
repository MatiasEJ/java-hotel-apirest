package com.hotel.controller;

import com.hotel.model.entity.Empleado;
import com.hotel.model.service.impl.EmpleadoServiceImpl;
import com.hotel.model.service.impl.UploadServiceImpl;
import com.sun.xml.bind.v2.model.core.ID;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class EmpleadoRestControllerTest {
    
    private static final long ID_EMPLEADO = 3l;
    public static final String BADUSH = "BADUSH";
    
    @InjectMocks
    EmpleadoRestController empleadoRestController;
    @Mock
    EmpleadoServiceImpl empleadoService;
    @Mock
    UploadServiceImpl uploadService;
   
    Empleado empleado;
    Empleado empleado2;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        empleado = new Empleado();
        empleado.setId(ID_EMPLEADO);
        empleado.setNombre(BADUSH);
    }
    
    @Test
    void create() {
    
    }
    
    @Tag("findById")
    @Nested
    class findEmpleadoById{
        @Test
        @DisplayName("Prueba DataAccessError.")
        void findEmpleadoById_DataAccessError() {
            
            when(empleadoService.findById(anyLong())).thenThrow(new DataAccessException("msg") {
                @Override
                public Throwable getMostSpecificCause() {
                    return super.getMostSpecificCause();
                }
            });
            ResponseEntity<?> response = empleadoRestController.findEmpleadoById(ID_EMPLEADO);
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
        }
        @Test
        void findEmpleadoById_NullEmpleado() {
            when(empleadoService.findById(anyLong())).thenReturn(null);
            ResponseEntity<?> response = empleadoRestController.findEmpleadoById(ID_EMPLEADO);
            assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        }
    
        @ParameterizedTest
        @ValueSource(longs = {1l,2l,3l})
        void findEmpleadoById_OK(Long id_test){
            when(empleadoService.findById(ID_EMPLEADO)).thenReturn(empleado);
            ResponseEntity<?> response = empleadoRestController.findEmpleadoById(ID_EMPLEADO);
            assertEquals(HttpStatus.OK,response.getStatusCode());

        }
        
    }
    
    
}