package com.ludoteca.api.service;

import com.ludoteca.api.dto.request.ReservaRequestDto;
import com.ludoteca.api.dto.response.ReservaResponseDto;
import com.ludoteca.api.enums.EstadoReserva;
import com.ludoteca.api.model.Mesa;
import com.ludoteca.api.model.Reserva;
import com.ludoteca.api.model.TurnoDia;
import com.ludoteca.api.model.Usuario;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final MesaRepository mesaRepository;
    private final TurnoDiaRepository turnoDiaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ReservaMapper reservaMapper;

    public List<ReservaResponseDto> listarMisReservas(Usuario usuario) {
        return reservaRepository.findByUsuario(usuario).stream()
                .map(reservaMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ReservaResponseDto crearReserva(ReservaRequestDto dto, Usuario usuario) {
        Mesa mesa = mesaRepository.findById(dto.getIdMesa())
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));

        TurnoDia turnoDia = turnoDiaRepository.findById(dto.getIdTurnoDia())
                .orElseThrow(() -> new RuntimeException("Turno no encontrado"));

        if (reservaRepository.existsByMesaAndTurnoDia(mesa, turnoDia)) {
            throw new RuntimeException("La mesa ya está reservada en ese turno");
        }

        Reserva reserva = new Reserva();
        reserva.setMesa(mesa);
        reserva.setTurnoDia(turnoDia);
        reserva.setUsuario(usuario);
        reserva.setEstado(EstadoReserva.ACTIVO);

        Reserva guardada = reservaRepository.save(reserva);
        return reservaMapper.toResponseDto(guardada);
    }

    @Transactional
    public void cancelarReserva(Long idReserva) {
        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        reserva.setEstado(EstadoReserva.CANCELADO);
        reservaRepository.save(reserva);
    }

    @Transactional
    public void cancelarTodasMisReservas(Usuario usuario) {
        List<Reserva> reservas = reservaRepository.findByUsuario(usuario);
        reservas.forEach(r -> r.setEstado(EstadoReserva.CANCELADO));
        reservaRepository.saveAll(reservas);
    }
}
