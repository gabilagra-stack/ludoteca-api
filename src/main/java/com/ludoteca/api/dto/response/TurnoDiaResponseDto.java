package com.ludoteca.api.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TurnoDiaResponseDto {

    private Long id;

    private LocalDate fecha;
    private String diaSemana;

    private Long turnoHorarioId;
    private String horario;
}
