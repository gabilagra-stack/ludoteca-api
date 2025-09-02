package com.ludoteca.api.service;

import com.ludoteca.api.dto.request.CrearUsuarioDto;
import com.ludoteca.api.dto.request.LoginRequestDto;
import com.ludoteca.api.dto.response.LoginResponseDto;
import com.ludoteca.api.dto.response.UsuarioResponseDto;
import com.ludoteca.api.exception.UsuarioYaExisteException;
import com.ludoteca.api.mapper.UsuarioMapper;
import com.ludoteca.api.model.Usuario;
import com.ludoteca.api.repository.UsuarioRepository;
import com.ludoteca.api.utils.JwtTokenProvider;
import com.ludoteca.api.utils.UsuarioPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UsuarioMapper usuarioMapper;

    public UsuarioResponseDto registrar(CrearUsuarioDto requestDto) {
        if (usuarioRepository.existsByEmail(requestDto.getEmail())) {
            throw new UsuarioYaExisteException("El correo ya está registrado");
        }

        Usuario usuario = usuarioMapper.toEntity(requestDto);
        usuario.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(usuario);
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );

        UsuarioPrincipal principal = (UsuarioPrincipal) authentication.getPrincipal();
        Usuario usuario = principal.getUsuario();
        String token = jwtTokenProvider.generarToken(usuario);

        return new LoginResponseDto(
                token,
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol().name()
        );
    }
}
