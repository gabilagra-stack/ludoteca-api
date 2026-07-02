package com.ludoteca.api.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JuegoDestacadoResponseDto {
    private Long id;
    private String titulo;
    private String cantidadJugadores;
    private String duracion;
    private LocalDateTime fechaCreacion;
}
