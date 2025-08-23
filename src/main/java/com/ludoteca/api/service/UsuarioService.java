package com.ludoteca.api.service;

import com.ludoteca.api.dto.request.CrearUsuarioDto;
import com.ludoteca.api.dto.response.UsuarioResponseDto;
import com.ludoteca.api.enums.RolUsuario;
import com.ludoteca.api.exception.UsuarioNoEncontradoExeption;
import com.ludoteca.api.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioResponseDto registrarUsuario(final CrearUsuarioDto request) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(request.getNombre());
        nuevoUsuario.setEmail(request.getEmail());
        nuevoUsuario.setPassword(passwordEncoder.encode(request.getContraseña()));
        nuevoUsuario.setRolUsuario(RolUsuario.CLIENTE);

        Usuario guardado = usuarioRepository.save(nuevoUsuario);
        return mapToDto(guardado);
    }

    public UsuarioResponseDto obtenerMiPerfil(final Usuario usuario) {
        return mapToDto(usuario);
    }

    public List<UsuarioResponseDto> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public void eliminarUsuario(final Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNoEncontradoExeption(id);
        }
        usuarioRepository.deleteById(id);
    }

    private UsuarioResponseDto mapToDto(final Usuario usuario) {
        return UsuarioResponseDto.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .rol(usuario.getRolUsuario().name())
                .build();
    }
}
