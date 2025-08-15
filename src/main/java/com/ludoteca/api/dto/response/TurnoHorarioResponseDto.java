package com.ludoteca.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurnoHorarioResponseDto {

    private Long id;
    private String horaInicio; // Formato HH:mm
    private String horaFin;
}
