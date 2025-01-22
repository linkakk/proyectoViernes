package com.equipo_2.ProyectoViernes.estadotarea.repository;

import com.equipo_2.ProyectoViernes.estadotarea.model.EstadoTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoTareaRepository extends JpaRepository<EstadoTarea, Long> {

    // Encuentra un estado por nombre
    Optional<EstadoTarea> findByNombre(String nombre);

    // Ejemplo adicional: encuentra estados cuya descripción contenga una palabra clave
    // List<EstadoTarea> findByDescripcionContaining(String keyword);
}
