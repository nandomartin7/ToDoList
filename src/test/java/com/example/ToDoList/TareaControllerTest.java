package com.example.ToDoList;

import com.example.ToDoList.Controller.TareaController;
import com.example.ToDoList.Service.TareaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print; // Importa print()
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TareaController.class)
public class TareaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Mock del servicio
    @MockitoBean
    private TareaService tareaService;

    @Test
    public void testConectadoEndpoint() throws Exception {
        mockMvc.perform(get("/tarea/conectado"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Proyecto funcionando"));
    }
}
