package com.ludoteca.api.dto.response;

import lombok.Data;

@Data
public class MesaResponseDto {
    private Long id;
    private int numero;
    private int capacidad;
    private boolean disponible;
}
