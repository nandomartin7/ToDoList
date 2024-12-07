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

    //Metodo para crear Tareas
    public Tarea crearTarea (Tarea tarea){
        tarea.setFecha(new Date());
        tarea.setEstado("Pendiente");
        return  tareaRepository.save(tarea);
    }

    //Metodo para listar Tareas
    public List<Tarea> listarTareas (){
        List<Tarea> tareas = tareaRepository.findAll();
        return tareas;
    }

    //Metodo para actualizar estado de una Tarea
    public Tarea actualizarEstado (Long idTarea, String nuevoEstado) throws Exception{
        Tarea tarea = tareaRepository.findByIdTarea(idTarea);
        if (tarea == null){
            throw new Exception("No se encontro una tarea con ese Id: "+idTarea);
        }
        tarea.setEstado(nuevoEstado);
        tareaRepository.save(tarea);
        return tarea;
    }

    //Metodo para eliminar una Tarea
    public boolean eliminarTarea (Long idTarea) throws Exception{
        Tarea tarea = tareaRepository.findByIdTarea(idTarea);
        if (tarea == null){
            throw new Exception("No se encontro una tarea con ese Id: "+idTarea);
        }
        tareaRepository.deleteById(idTarea);
        return true;
    }

}
