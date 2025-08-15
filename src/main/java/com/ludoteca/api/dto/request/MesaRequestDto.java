package com.ludoteca.api.dto.request;

import lombok.Data;

@Data
public class MesaRequestDto {
    private int numero;
    private int capacidad;
    private boolean disponible;
}
