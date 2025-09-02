package com.ludoteca.api.dto.response;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservaResponseDto {
    private Long id;
    private Long usuarioId;
    private String nombreUsuario;
    private Long mesaId;
    private Integer numeroMesa;
    private Long turnoDiaId;
    private String diaSemana;
    private LocalDate fechaTurno;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String estado;
}
