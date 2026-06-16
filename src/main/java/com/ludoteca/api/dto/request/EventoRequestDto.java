package com.ludoteca.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventoRequestDto {

    @NotBlank(message = "El titulo es obligatorio")
    @Size(max = 150, message = "El titulo no puede superar los 150 caracteres")
    private String titulo;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    private String descripcion;

    @NotBlank(message = "La url es obligatoria")
    private String url;
}
