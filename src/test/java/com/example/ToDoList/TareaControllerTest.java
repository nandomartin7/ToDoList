package com.example.ToDoList;

import com.example.ToDoList.Controller.TareaController;
import com.example.ToDoList.Model.Tarea;
import com.example.ToDoList.Service.TareaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print; // Importa print()
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TareaController.class)
public class TareaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Mock del servicio
    @MockitoBean
    private TareaService tareaService;

    // 1. Verificar conexión al sistema
    @Test
    public void testConectadoEndpoint() throws Exception {
        mockMvc.perform(get("/tarea/conectado"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Proyecto funcionando"));
    }

    // 2. Probar creación de una tarea
    @Test
    public void testCrearTarea() throws Exception {
        Tarea tarea = new Tarea();
        tarea.setIdTarea(1L);
        tarea.setTitulo("Nueva tarea");
        tarea.setDescripcion("Descripción de la tarea");

        when(tareaService.crearTarea(Mockito.any(Tarea.class))).thenReturn(tarea);

        mockMvc.perform(post("/tarea")
                        .contentType("application/json")
                        .content("{\"titulo\": \"Nueva tarea\", \"descripcion\": \"Descripción de la tarea\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Se registro la tarea:")));
    }

    // 3. Probar listado de tareas
    @Test
    public void testListarTareas() throws Exception {
        Tarea tarea1 = new Tarea();
        tarea1.setIdTarea(1L);
        tarea1.setTitulo("Tarea 1");
        tarea1.setDescripcion("Descripción tarea 1");

        when(tareaService.listarTareas()).thenReturn(Arrays.asList(tarea1));

        mockMvc.perform(get("/tarea"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Tarea 1"));
    }
}
