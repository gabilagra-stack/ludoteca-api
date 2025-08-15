package com.ludoteca.api.dto.response;

import java.time.LocalDate;

public class ReservaResponseDto {
    private Long id;
    private Long usuarioId;
    private String nombreUsuario;
    private Long mesaId;
    private Integer numeroMesa;
    private Long turnoDiaId;
    private String diaSemana;
    private LocalDate fechaTurno;
    private String horarioTurno;
    private String estado;
}
