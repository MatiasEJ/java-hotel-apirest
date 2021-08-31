package com.hotel.controller;

import com.hotel.model.service.HabitacionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class HabitacionRestControllerTest {
    
    @Mock
    HabitacionService habitacionService;
    HabitacionRestController controller;
    
    @BeforeEach
    void setUp() {
    }
    
    @Test
    void index() {
    }
}