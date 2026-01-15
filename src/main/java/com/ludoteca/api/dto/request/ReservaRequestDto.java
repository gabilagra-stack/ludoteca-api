package com.ludoteca.api.dto.request;

import lombok.Data;

@Data
public class ReservaRequestDto {
    private Long usuarioId;
    private Long mesaId;
    private Long turnoDiaId;
}
