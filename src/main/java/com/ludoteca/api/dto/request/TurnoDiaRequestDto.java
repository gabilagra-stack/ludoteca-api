package com.ludoteca.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDate;

@Data
public class TurnoDiaRequestDto {

    @NotNull
    private LocalDate fecha;

    @NotNull
    private String diaSemana;

    @NotNull
    private Long turnoHorarioId;
}
