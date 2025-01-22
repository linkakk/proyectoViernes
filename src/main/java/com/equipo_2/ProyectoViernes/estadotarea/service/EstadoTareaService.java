package com.equipo_2.ProyectoViernes.estadotarea.service;

import com.equipo_2.ProyectoViernes.estadotarea.dto.EstadoTareaDTO;
import com.equipo_2.ProyectoViernes.estadotarea.exception.EstadoTareaNotFoundException;
import com.equipo_2.ProyectoViernes.estadotarea.model.EstadoTarea;
import com.equipo_2.ProyectoViernes.estadotarea.repository.EstadoTareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoTareaService {

    private final EstadoTareaRepository estadoTareaRepository;

    public EstadoTareaService(EstadoTareaRepository estadoTareaRepository) {
        this.estadoTareaRepository = estadoTareaRepository;
    }

    /**
     * Crear un nuevo estado de tarea.
     *
     * @param estadoTareaDTO Datos del estado de tarea.
     * @return DTO del estado de tarea creado.
     */
    public EstadoTareaDTO crearEstadoTarea(EstadoTareaDTO estadoTareaDTO) {
        validarEstadoTarea(estadoTareaDTO); // Validación previa
        EstadoTarea estadoTarea = convertirAEntidad(estadoTareaDTO);
        EstadoTarea creado = estadoTareaRepository.save(estadoTarea);
        return convertirADTO(creado);
    }

    /**
     * Listar todos los estados de tarea.
     *
     * @return Lista de DTOs de estados de tarea.
     */
    public List<EstadoTareaDTO> listarEstadosTarea() {
        return estadoTareaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    /**
     * Buscar un estado de tarea por ID.
     *
     * @param id ID del estado de tarea.
     * @return DTO del estado de tarea.
     */
    public EstadoTareaDTO buscarPorId(Long id) {
        EstadoTarea estadoTarea = estadoTareaRepository.findById(id)
                .orElseThrow(() -> new EstadoTareaNotFoundException("Estado de tarea con ID: " + id + " no encontrado"));
        return convertirADTO(estadoTarea);
    }

    /**
     * Eliminar un estado de tarea por ID.
     *
     * @param id ID del estado de tarea.
     */
    public void eliminarEstadoTarea(Long id) {
        estadoTareaRepository.deleteById(id);
    }

    /**
     * Validar un estado de tarea antes de guardarlo.
     *
     * @param dto DTO del estado de tarea.
     */
    private void validarEstadoTarea(EstadoTareaDTO dto) {
        if (dto.getNombre() == null || dto.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del estado de tarea no puede estar vacío.");
        }
    }

    public EstadoTarea convertirAEntidad(EstadoTareaDTO dto) {
        return new EstadoTarea(
                dto.getId(),
                dto.getNombre(),
                dto.getDescripcion()
        );
    }

    public EstadoTareaDTO convertirADTO(EstadoTarea entidad) {
        return new EstadoTareaDTO(entidad.getId(), entidad.getNombre(), entidad.getDescripcion());
    }
}
