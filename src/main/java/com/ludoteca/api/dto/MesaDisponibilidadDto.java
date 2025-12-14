package com.ludoteca.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MesaDisponibilidadDto {
    private Integer id;
    private Integer numero;
    private Integer capacidad;
    private Boolean disponible;
}
