package com.ludoteca.api.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EventoResponseDto {
    private Long id;
    private String titulo;
    private LocalDate fecha;
    private String descripcion;
    private String url;
}
