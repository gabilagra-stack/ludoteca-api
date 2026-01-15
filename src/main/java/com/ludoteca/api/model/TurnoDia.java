package com.ludoteca.api.model;

import com.ludoteca.api.enums.DiaSemana;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "turno_dia")
public class TurnoDia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana", nullable = false)
    private DiaSemana diaSemana;

    @ManyToOne
    @JoinColumn(name = "turno_horario_id", nullable = false)
    private TurnoHorario turnoHorario;
}
