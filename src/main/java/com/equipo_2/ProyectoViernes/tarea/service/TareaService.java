package com.equipo_2.ProyectoViernes.tarea.service;

import com.equipo_2.ProyectoViernes.estadotarea.model.EstadoTarea;
import com.equipo_2.ProyectoViernes.tarea.dto.TareaDTO;
import com.equipo_2.ProyectoViernes.tarea.exception.TareaaNotFoundException;
import com.equipo_2.ProyectoViernes.tarea.model.Tarea;
import com.equipo_2.ProyectoViernes.tarea.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    // Crear una nueva tarea
    public Tarea crearTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    // Listar todas las tareas
    public List<TareaDTO> listarTareas() {
        return tareaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Buscar una tarea por ID
    public TareaDTO buscarTarea(Long id) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new TareaaNotFoundException("Tarea con ID: " + id + " no encontrada"));
        return convertirADTO(tarea);
    }

    // Eliminar una tarea por ID
    public void eliminarTarea(Long id) {
        if (!tareaRepository.existsById(id)) {
            throw new TareaaNotFoundException("Tarea con ID: " + id + " no encontrada");
        }
        tareaRepository.deleteById(id);
    }
    public Tarea buscarTareaPorIdEntidad(Long id) {
        return tareaRepository.findById(id)
                .orElseThrow(() -> new TareaaNotFoundException("Tarea con ID: " + id + " no encontrada"));
    }

    // Conversión de DTO a entidad
    public Tarea convertirAEntidad(TareaDTO dto) {
        Tarea tarea = new Tarea();
        tarea.setId(dto.getId());
        tarea.setNombre(dto.getNombre());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setFechaFinalizacion(dto.getFechaFinalizacion());
        tarea.isFinalizada();

        // Asignar el EstadoTarea basado en el ID del DTO
        if (dto.getEstadoTareaId() != null) {
            EstadoTarea estadoTarea = new EstadoTarea();
            estadoTarea.setId(dto.getEstadoTareaId());
            tarea.setEstadoTarea(estadoTarea);
        }

        return tarea;
    }

    // Conversión de entidad a DTO
    public TareaDTO convertirADTO(Tarea tarea) {
        return new TareaDTO(
                tarea.getId(),
                tarea.getNombre(),
                tarea.getDescripcion(),
                tarea.getFechaFinalizacion(),
                tarea.getEstadoTarea().getId(), // EstadoTarea ID
                tarea.getEstadoTarea().getNombre(),
                tarea.isFinalizada()// EstadoTarea Nombre
        );
    }
}
