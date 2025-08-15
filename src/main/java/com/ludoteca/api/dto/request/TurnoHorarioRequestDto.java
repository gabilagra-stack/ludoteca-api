package com.ludoteca.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurnoHorarioRequestDto {
    private LocalTime horaInicio;
    private LocalTime horaFin;
}
