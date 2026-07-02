package com.ludoteca.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class JuegoDestacadoRequestDto {

    @NotBlank(message = "El titulo es obligatorio")
    @Size(max = 150, message = "El titulo no puede superar los 150 caracteres")
    private String titulo;

    @NotBlank(message = "La cantidad de jugadores es obligatoria")
    @Size(max = 50, message = "La cantidad de jugadores no puede superar los 50 caracteres")
    private String cantidadJugadores;

    @NotBlank(message = "La duracion es obligatoria")
    @Size(max = 50, message = "La duracion no puede superar los 50 caracteres")
    private String duracion;
}
