package com.ludoteca.api.controller;

import com.ludoteca.api.dto.request.CrearUsuarioDto;
import com.ludoteca.api.dto.response.UsuarioResponseDto;
import com.ludoteca.api.model.Usuario;
import com.ludoteca.api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioCrontroller {

    private final UsuarioService usuarioService;

    // Registro de usuario
    public ResponseEntity<UsuarioResponseDto> registrarUsuario(@RequestBody final CrearUsuarioDto crearUsuarioDto) {
        UsuarioResponseDto response = usuarioService.registrarUsuario(crearUsuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Obtener mi perfil
    @GetMapping("/perfil")
    public ResponseEntity<UsuarioResponseDto> obtenerPerfil(@AuthenticationPrincipal final Usuario usuario) {
        UsuarioResponseDto response = usuarioService.obtenerMiPerfil(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Listar todos los usuarios (solo admin)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    // Eliminar un usuario por ID (solo admin)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable final Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
