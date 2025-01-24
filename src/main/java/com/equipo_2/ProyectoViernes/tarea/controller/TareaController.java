package com.equipo_2.ProyectoViernes.tarea.controller;

import com.equipo_2.ProyectoViernes.tarea.dto.TareaDTO;
import com.equipo_2.ProyectoViernes.tarea.model.Tarea;
import com.equipo_2.ProyectoViernes.tarea.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tarea")
@Validated
public class TareaController {

    private final TareaService tareaService;

    @Autowired
    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    /**
     * Crear una nueva tarea
     */
    @PostMapping
    public ResponseEntity<TareaDTO> crearTarea(@Valid @RequestBody TareaDTO tareaDTO) {
        // Conversión a entidad
        Tarea tareaEntidad = tareaService.convertirAEntidad(tareaDTO);
        // Llamar al servicio para crear la tarea
        Tarea tareaCreada = tareaService.crearTarea(tareaEntidad);
        // Conversión de la tarea creada a DTO para la respuesta
        TareaDTO respuestaDTO = tareaService.convertirADTO(tareaCreada);
        return ResponseEntity.ok(respuestaDTO);
    }

    /**
     * Listar todas las tareas
     */
    @GetMapping
    public ResponseEntity<List<TareaDTO>> listarTarea() {
        List<TareaDTO> listas = tareaService.listarTareas();
        return ResponseEntity.ok(listas);
    }

    /**
     * Buscar una tarea por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<TareaDTO> buscarTareaPorId(@PathVariable Long id) {
        TareaDTO tareaDTO = tareaService.buscarTarea(id);
        return ResponseEntity.ok(tareaDTO);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<TareaDTO>> filtrarTareas(@RequestParam boolean finalizadas) {
        List<TareaDTO> tareas = tareaService.listarTareas()
                .stream()
                .filter(t -> t.isFinalizada() == finalizadas)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tareas);
    }


    /**
     * Eliminar una tarea por ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> finalizarTarea(@PathVariable Long id) {
        Tarea tarea = tareaService.buscarTareaPorIdEntidad(id); // Buscar la tarea por ID (asegúrate de tener este método en el servicio).
        if (tarea == null) {
            return ResponseEntity.status(404).body(
                    new java.util.HashMap<String, String>() {{
                        put("message", "Tarea con ID: " + id + " no encontrada");
                    }}
            );
        }

        tarea.setFinalizada(true); // Marcar como finalizada
        tareaService.crearTarea(tarea); // Guardar el cambio

        // Respuesta consistente en JSON
        return ResponseEntity.ok().body(
                new java.util.HashMap<String, String>() {{
                    put("message", "Tarea con ID: " + id + " marcada como finalizada correctamente");
                }}
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TareaDTO> actualizarTarea(@PathVariable   Long id, @Valid @RequestBody TareaDTO tareaDTO){
        Tarea tareaActualizada = tareaService.actualizarTarea(id, tareaDTO);

        TareaDTO respuestaDTO = tareaService.convertirADTO(tareaActualizada);
        return ResponseEntity.ok(respuestaDTO);
    }

}
