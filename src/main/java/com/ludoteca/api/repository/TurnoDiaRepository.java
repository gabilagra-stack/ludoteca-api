package com.ludoteca.api.repository;

import com.ludoteca.api.enums.DiaSemana;
import com.ludoteca.api.model.Reserva;
import com.ludoteca.api.model.TurnoDia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TurnoDiaRepository extends JpaRepository<TurnoDia, Integer> {

    List<TurnoDia> findByFecha(LocalDate fecha);
    Optional<TurnoDia> findByFechaAndTurnoHorarioId(LocalDate fecha, Long turnoHorarioId);

    @Query("SELECT t FROM TurnoDia t "
            + "WHERE(:fechaTurno is null or :fechaTurno = t.fecha ) "
            + "AND (:diaSemana is null or :diaSemana = t.diaSemana ) "
            + "ORDER BY t.fecha DESC")
    List<TurnoDia> busquedaPorFiltros(LocalDate fechaTurno, DiaSemana diaSemana);

    List<TurnoDia> findAllByOrderByFechaDesc();
    List<TurnoDia> findByFechaOrderByFechaDesc(LocalDate fecha);
    List<TurnoDia> findByDiaSemanaOrderByFechaDesc(DiaSemana diaSemana);
    List<TurnoDia> findByFechaAndDiaSemanaOrderByFechaDesc(LocalDate fecha, DiaSemana diaSemana);
}
