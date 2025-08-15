package com.ludoteca.api.dto.response;

import lombok.Data;

@Data
public class UsuarioDto {
    private Long id;
    private String nombre;
    private String email;
    private String rol;
}
