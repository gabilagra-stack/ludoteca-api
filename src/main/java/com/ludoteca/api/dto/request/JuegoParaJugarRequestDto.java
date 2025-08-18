package com.ludoteca.api.dto.request;

import lombok.Data;

@Data
public class JuegoParaJugarRequestDto {
    private String nombre;
    private String descripcion;
    private Integer cantidadDisponible;
    private String imagenUrl;
    private Integer numeroMaximo;
    private String dificultad;
    private String categoria;
    private String duracionAproximada;
}
