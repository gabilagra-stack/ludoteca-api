package com.ludoteca.api.dto.response;

import com.ludoteca.api.enums.RolUsuario;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponseDto {
    private Long id;
    private String nombre;
    private String email;
    private RolUsuario rol;
}
