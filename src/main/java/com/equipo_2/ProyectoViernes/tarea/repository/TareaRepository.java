package com.equipo_2.ProyectoViernes.tarea.repository;

import com.equipo_2.ProyectoViernes.tarea.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TareaRepository extends JpaRepository<Tarea,Long> {

    List<Tarea> findByNombre(String nombre);
}
