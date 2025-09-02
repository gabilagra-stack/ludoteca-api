package com.ludoteca.api.utils;

import com.ludoteca.api.model.Usuario;
import com.ludoteca.api.repository.ReservaRepository;
import com.ludoteca.api.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service("reservaSecurityService")
public class ReservaSecurityService {
    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;

    public ReservaSecurityService(final ReservaRepository reservaRepository,
                                  final UsuarioRepository usuarioRepository) {
        this.reservaRepository = reservaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public boolean esDuenoDeReserva(Long id, Authentication authentication) {
        // Ajustá según tu UserDetails
        var principal = (UsuarioPrincipal) authentication.getPrincipal();
        Usuario usuario = usuarioRepository.findByEmail(principal.getUsuario().getEmail()).get();
        Long userId = usuario.getId(); // o email, username, etc.

        return reservaRepository.findById(id)
                .map(reserva -> reserva.getUsuario().getId().equals(userId))
                .orElse(false);
    }

}
