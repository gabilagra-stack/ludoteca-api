package com.ludoteca.api.repository;

import com.ludoteca.api.model.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    @Query("SELECT e FROM Evento e "
            + "WHERE (:titulo is null or LOWER(e.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))) "
            + "AND (:fechaDesde is null or e.fecha >= :fechaDesde) "
            + "AND (:fechaHasta is null or e.fecha <= :fechaHasta)")
    Page<Evento> busquedaPorFiltros(String titulo, LocalDate fechaDesde, LocalDate fechaHasta, Pageable pageable);
}
