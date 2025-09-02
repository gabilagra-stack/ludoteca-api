package com.ludoteca.api.repository;

import com.ludoteca.api.enums.DiaSemana;
import com.ludoteca.api.model.Reserva;
import com.ludoteca.api.model.TurnoDia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TurnoDiaRepository extends JpaRepository<TurnoDia, Long> {

    List<TurnoDia> findByFecha(LocalDate fecha);
    Optional<TurnoDia> findByFechaAndTurnoHorarioId(LocalDate fecha, Long turnoHorarioId);

    @Query("SELECT t FROM TurnoDia t "
            + "WHERE(:fechaTurno is null or :fechaTurno = t.fecha ) "
            + "AND (:diaSemana is null or :diaSemana = t.diaSemana ) "
            + "ORDER BY t.fecha DESC")
    List<TurnoDia> busquedaPorFiltros(LocalDate fechaTurno, DiaSemana diaSemana);
}
