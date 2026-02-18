package com.henrypb.dbSingers.repository;

import com.henrypb.dbSingers.model.Cancion;
import com.henrypb.dbSingers.model.Cantante;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CantanteRepository extends JpaRepository<Cantante, Long> {
    @NotNull List<Cantante> findAll();

    @Query("SELECT c FROM Cantante a JOIN a.canciones c ")
    List<Cancion> todasLasCanciones();

    Optional<Cantante> findByNombreContainsIgnoreCase(String nombreCantante);

    @Query("""
       SELECT c
       FROM Cantante a
       JOIN a.canciones c
       WHERE a.nombre ILIKE  %:nombre%
       """)
    List<Cancion> cancionesPorNombreCantante(@Param("nombre") String nombre);


}
