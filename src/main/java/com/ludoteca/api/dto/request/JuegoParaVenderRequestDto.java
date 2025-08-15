package com.ludoteca.api.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class JuegoParaVenderRequestDto {
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock;
    private String imagenUrl;
    private Integer numeroMaximo;
    private String dificultad;
    private String categoria;
    private String duracionAproximada;
}
