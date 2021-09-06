package com.hotel.model.service.impl;

import com.hotel.model.dao.HabitacionDao;
import com.hotel.model.entity.Habitacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

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
    Habitacion habitacion;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        habitacion = new Habitacion(PRECIO_NOCHE, NUM_ID);
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
        when(habitacionDao.save(any(Habitacion.class))).thenReturn(habitacion);
        when(habitacionDao.save(any(Habitacion.class))).thenReturn(Habitacion.builder().id(2l).build());
        Habitacion nuevaHab = habitacionService.save(habitacion);
        Habitacion nuevaHab2 = habitacionService.save(Habitacion.builder().id(2l).build());
        verify(habitacionDao).save(habitacion);
        assertNotNull(nuevaHab);
        assertEquals(2l, nuevaHab2.getId());
    }
    
    @ParameterizedTest
    @ValueSource(longs = {1l,2l,3l,4l})
    void save_incremental(Long ID_TEST) {
        //Give
        when(habitacionDao.save(any(Habitacion.class))).then(new Answer<Habitacion>(){
            Long sequence = ID_TEST;
            @Override
            public Habitacion answer(InvocationOnMock invocationOnMock) throws Throwable {
                Habitacion habitacion = invocationOnMock.getArgument(0);
                habitacion.setId(sequence++);
               return habitacion;
            }
        });
        //when
       Habitacion nuevaHab = habitacionService.save(habitacion);
       //then
       assertEquals(ID_TEST,nuevaHab.getId());
    }
    @Test
    void save_incremental_param() {
        //Given
        when(habitacionDao.save(any(Habitacion.class))).then(new Answer<Habitacion>(){
            Long sequence =1l;
            @Override
            public Habitacion answer(InvocationOnMock invocationOnMock) throws Throwable {
                Habitacion habitacion = invocationOnMock.getArgument(0);
                habitacion.setId(sequence++);
                return habitacion;
            }
        });
        //when
        Habitacion nuevaHab = habitacionService.save(habitacion);
        assertEquals(1l,nuevaHab.getId());
        Habitacion nuevaHab2= habitacionService.save(habitacion);
        verify(habitacionDao,times(2)).save(any(Habitacion.class));
        //then
        assertEquals(2l,nuevaHab2.getId());
    }
    
    @Test
    void testFindHabitacionById() {
        when(habitacionDao.findById(anyLong())).thenReturn(Optional.of(habitacion));
        Habitacion hab = habitacionService.findHabitacionById(habitacion.getId());
        assertNotNull(hab);
        assertEquals(PRECIO_NOCHE,hab.getPrecio_noche());
        verify(habitacionDao, times(1)).findById(hab.getId());
    }
    
    
    
    
}