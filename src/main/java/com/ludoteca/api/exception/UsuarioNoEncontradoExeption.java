package com.ludoteca.api.exception;

public class UsuarioNoEncontradoExeption extends RuntimeException{
    public UsuarioNoEncontradoExeption(Long id) {
        super("Usuario no encontrado con el id " + id);
    }
}
