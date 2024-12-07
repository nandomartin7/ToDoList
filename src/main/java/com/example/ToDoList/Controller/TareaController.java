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

    @PostMapping()
    public ResponseEntity<String> crearTarea(@RequestBody Tarea tarea){
        Tarea nuevaTarea = tareaService.crearTarea(tarea);
        return ResponseEntity.ok("Se registro la tarea:\nId:"+nuevaTarea.getIdTarea()+
                "\nTitulo: "+nuevaTarea.getTitulo()+"\nDescripcion: "+nuevaTarea.getDescripcion());
    }

    @GetMapping()
    public List<Tarea> listarTareas(){
        List<Tarea> tareas = tareaService.listarTareas();
        return tareas;
    }

    @PatchMapping("/{idTarea}")
    public ResponseEntity<Tarea> actualizarTarea(@PathVariable Long idTarea, @RequestParam String estado) throws Exception{
        Tarea tareaActualizada = tareaService.actualizarEstado(idTarea, estado);
        return tareaActualizada != null ? ResponseEntity.ok(tareaActualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idTarea}")
    public ResponseEntity<Void> eliminarTarea (@PathVariable Long idTarea) throws Exception{
        return tareaService.eliminarTarea(idTarea) ? ResponseEntity.noContent().build() :ResponseEntity.notFound().build();
    }
}