package com.ludoteca.api.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QueEstaPasandoResponseDto {
    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaCreacion;
}
