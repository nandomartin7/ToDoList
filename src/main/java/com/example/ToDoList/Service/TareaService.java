package com.example.ToDoList.Service;

import com.example.ToDoList.Model.Tarea;
import com.example.ToDoList.Repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TareaService {
    @Autowired
    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }
}
