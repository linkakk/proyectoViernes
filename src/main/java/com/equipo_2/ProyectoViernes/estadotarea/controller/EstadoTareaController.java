package com.equipo_2.ProyectoViernes.estadotarea.controller;

import com.equipo_2.ProyectoViernes.estadotarea.dto.EstadoTareaDTO;
import com.equipo_2.ProyectoViernes.estadotarea.service.EstadoTareaService;
import com.equipo_2.ProyectoViernes.tarea.dto.TareaDTO;
import com.equipo_2.ProyectoViernes.tarea.model.Tarea;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estado-tareas")
public class EstadoTareaController {

    private final EstadoTareaService estadoTareaService;

    public EstadoTareaController(EstadoTareaService estadoTareaService) {
        this.estadoTareaService = estadoTareaService;
    }

    // Crear un nuevo estado de tarea
    @PostMapping
    public ResponseEntity<EstadoTareaDTO> crearEstadoTarea( @RequestBody EstadoTareaDTO estadoTareaDTO) {
        EstadoTareaDTO creado = estadoTareaService.crearEstadoTarea(estadoTareaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // Listar todos los estados de tarea
    @GetMapping
    public ResponseEntity<List<EstadoTareaDTO>> listarEstadoTarea() {
        List<EstadoTareaDTO> estados = estadoTareaService.listarEstadosTarea();
        return ResponseEntity.ok(estados);
    }

    // Buscar estado de tarea por ID
    @GetMapping("/{id}")
    public ResponseEntity<EstadoTareaDTO> buscarEstadoTareaPorId(@PathVariable Long id) {
        EstadoTareaDTO estado = estadoTareaService.buscarPorId(id);
        return ResponseEntity.ok(estado);
    }

    // Eliminar un estado de tarea por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEstadoTarea(@PathVariable Long id) {
        estadoTareaService.eliminarEstadoTarea(id);
        return ResponseEntity.ok("Estado de tarea con ID: " + id + " eliminado correctamente");
    }

}
