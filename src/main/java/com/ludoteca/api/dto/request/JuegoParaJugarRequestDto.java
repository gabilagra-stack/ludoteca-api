package com.ludoteca.api.dto.request;

import com.ludoteca.api.enums.Dificultad;
import lombok.Data;

@Data
public class JuegoParaJugarRequestDto {
    private String nombre;
    private String descripcion;
    private Integer cantidadDisponible;
    private String imagenUrl;
    private Integer numeroMaximo;
    private Dificultad dificultad;
    private String categoria;
    private String duracionAproximada;
}
