package com.ludoteca.api.dto.request;

import lombok.Data;

@Data
public class CrearUsuarioDto {
    private String nombre;
    private String email;
    private String password;
}
