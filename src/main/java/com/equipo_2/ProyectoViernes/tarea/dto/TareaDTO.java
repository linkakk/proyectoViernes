package com.equipo_2.ProyectoViernes.tarea.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) para representar una tarea.
 */
public class TareaDTO {

    private Long id;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @NotNull(message = "La descripción no puede ser nula")
    private String descripcion;

    @NotNull(message = "La fecha de finalización no puede ser nula")
    private LocalDate fechaFinalizacion;

    private Long estadoTareaId;
    private String estadoTareaNombre;
    private boolean isFinalizada;

    /**
     * Constructor completo.
     *
     * @param id                ID de la tarea.
     * @param nombre            Nombre de la tarea.
     * @param descripcion       Descripción de la tarea.
     * @param fechaFinalizacion Fecha de finalización de la tarea.
     * @param estadoTareaId     ID del estado de la tarea.
     * @param estadoTareaNombre Nombre del estado de la tarea.
     *
     */
    public TareaDTO(Long id, String nombre, String descripcion, LocalDate fechaFinalizacion, Long estadoTareaId, String estadoTareaNombre,Boolean  isFinalizada) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaFinalizacion = fechaFinalizacion;
        this.estadoTareaId = estadoTareaId;
        this.estadoTareaNombre = estadoTareaNombre;
        this.isFinalizada = isFinalizada;
    }

    // Getters y Setters

    public boolean isFinalizada() {
        return isFinalizada;
    }

    public void setFinalizada(boolean finalizada) {
        isFinalizada = finalizada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public Long getEstadoTareaId() {
        return estadoTareaId;
    }

    public void setEstadoTareaId(Long estadoTareaId) {
        this.estadoTareaId = estadoTareaId;
    }

    public String getEstadoTareaNombre() {
        return estadoTareaNombre;
    }

    public void setEstadoTareaNombre(String estadoTareaNombre) {
        this.estadoTareaNombre = estadoTareaNombre;
    }
}
