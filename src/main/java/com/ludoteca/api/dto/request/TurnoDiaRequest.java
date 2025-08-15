package com.ludoteca.api.dto.request;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Data
public class TurnoDiaRequest {

    @NotNull
    private LocalDate fecha;

    @NotNull
    private Long turnoHorarioId;
}
