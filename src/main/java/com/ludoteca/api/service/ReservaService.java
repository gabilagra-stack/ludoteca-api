package com.ludoteca.api.service;

import com.ludoteca.api.dto.request.ReservaRequestDto;
import com.ludoteca.api.dto.response.ReservaResponseDto;
import com.ludoteca.api.enums.EstadoReserva;
import com.ludoteca.api.mapper.ReservaMapper;
import com.ludoteca.api.model.Mesa;
import com.ludoteca.api.model.Reserva;
import com.ludoteca.api.model.TurnoDia;
import com.ludoteca.api.model.Usuario;
import com.ludoteca.api.repository.MesaRepository;
import com.ludoteca.api.repository.ReservaRepository;
import com.ludoteca.api.repository.TurnoDiaRepository;
import com.ludoteca.api.repository.UsuarioRepository;
import com.ludoteca.api.utils.UsuarioPrincipal;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final MesaRepository mesaRepository;
    private final TurnoDiaRepository turnoDiaRepository;
    private final ReservaMapper reservaMapper;
    private final UsuarioRepository usuarioRepository;

    public List<ReservaResponseDto> listarMisReservas(UsuarioPrincipal usuarioPrincipal) {
        Usuario usuario = usuarioRepository.findByEmail(usuarioPrincipal.getUsuario().getEmail()).get();
        return  reservaMapper.toList(reservaRepository.findListByUsuario(usuario));
    }

    @Transactional
    public ReservaResponseDto crearReserva(ReservaRequestDto dto, UsuarioPrincipal usuarioPrincipal) {
        Mesa mesa = mesaRepository.findById(dto.getMesaId())
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));

        TurnoDia turnoDia = turnoDiaRepository.findById(dto.getTurnoDiaId())
                .orElseThrow(() -> new RuntimeException("Turno no encontrado"));

        if (reservaRepository.existsByMesaAndTurnoDia(mesa, turnoDia)) {
            throw new RuntimeException("La mesa ya está reservada en ese turno");
        }
        Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioPrincipal.getUsuario().getEmail());
        if (!usuario.isPresent()) {
            throw new RuntimeException("Usuario no encontrado");
        }
        Reserva reserva = new Reserva();
        reserva.setMesa(mesa);
        reserva.setTurnoDia(turnoDia);
        reserva.setUsuario(usuario.get());
        reserva.setEstado(EstadoReserva.RESERVADO);
        return reservaMapper.toDto(reservaRepository.save(reserva));
    }

    @Transactional
    public void cancelarReserva(Long idReserva) {
        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        reserva.setEstado(EstadoReserva.CANCELADO);
        reservaRepository.save(reserva);
    }

    public List<ReservaResponseDto> listarReservas(final String nombreUsuario, final Integer numeroMesa,
                                                   final LocalDate fechaTurno, final String diaSemana) {
        return reservaMapper.toList(reservaRepository.busquedaPorFiltros(nombreUsuario, numeroMesa, fechaTurno,
                                                                            diaSemana));
    }
}
