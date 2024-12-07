package com.example.ToDoList.Controller;

import com.example.ToDoList.Model.Tarea;
import com.example.ToDoList.Service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarea")
public class TareaController {
    @Autowired
    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping("/conectado")
    public String conectado(){
        return "Proyecto funcionando";
    }

}
