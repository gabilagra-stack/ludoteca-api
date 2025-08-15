package com.ludoteca.api.dto.response;

import lombok.Data;

@Data
public class JuegoParaJugarResponseDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer cantidadDisponible;
    private String imagenUrl;
    private Integer numeroMaximo;
    private String dificultad;
    private String categoria;
    private String duracionAproximada;
}
