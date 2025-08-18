package com.ludoteca.api.controller;

import com.ludoteca.api.dto.request.CrearUsuarioDto;
import com.ludoteca.api.dto.response.UsuarioDto;
import com.ludoteca.api.model.Usuario;
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
    public ResponseEntity<UsuarioDto> registrarUsuario(@RequestBody final CrearUsuarioDto crearUsuarioDto) {
        UsuarioDto response = usuarioService.registrarUsuario(crearUsuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Obtener mi perfil
    @GetMapping("/perfil")
    public ResponseEntity<UsuarioDto> obtenerPerfil(@AuthenticationPrincipal final Usuario usuario) {
        UsuarioDto response = usuarioService.obtenerMiPerfil(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Listar todos los usuarios (solo admin)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> listarUsuarios() {
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
