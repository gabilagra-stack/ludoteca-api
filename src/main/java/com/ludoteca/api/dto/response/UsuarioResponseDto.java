package com.ludoteca.api.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponseDto {
    private Long id;
    private String nombre;
    private String email;
    private String rol;
}
