package com.ludoteca.api.service;

import com.ludoteca.api.dto.request.CrearUsuarioDto;
import com.ludoteca.api.dto.response.UsuarioResponseDto;
import com.ludoteca.api.enums.RolUsuario;
import com.ludoteca.api.exception.UsuarioNoEncontradoException;
import com.ludoteca.api.mapper.UsuarioMapper;
import com.ludoteca.api.model.Usuario;
import com.ludoteca.api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    public UsuarioResponseDto registrarUsuario(final CrearUsuarioDto request) {
        Usuario usuario = usuarioMapper.toEntity(request);
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(RolUsuario.CLIENTE);
        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    public UsuarioResponseDto obtenerMiPerfil(final Usuario usuario) {
        return usuarioMapper.toDto(usuario);
    }

    public List<UsuarioResponseDto> listarUsuarios(final String nombre, final String email,
                                                   final RolUsuario rolUsuario) {
        return usuarioMapper.toListDto(usuarioRepository.busquedaPorFiltros(nombre, email, rolUsuario));
    }

    public void eliminarUsuario(final Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNoEncontradoException("Usuario no encontrado con el id " + id);
        }
        usuarioRepository.deleteById(id);
    }
}
