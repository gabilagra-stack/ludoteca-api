package com.ludoteca.api.service;

import com.ludoteca.api.dto.MesaDisponibilidadDto;
import com.ludoteca.api.dto.request.ReservaRequestDto;
import com.ludoteca.api.dto.response.DisponibilidadTurnoResponseDto;
import com.ludoteca.api.dto.response.ReservaResponseDto;
import com.ludoteca.api.enums.DiaSemana;
import com.ludoteca.api.enums.EstadoReserva;
import com.ludoteca.api.exception.*;
import com.ludoteca.api.mapper.ReservaMapper;
import com.ludoteca.api.model.Mesa;
import com.ludoteca.api.model.Reserva;
import com.ludoteca.api.model.TurnoDia;
import com.ludoteca.api.model.Usuario;
import com.ludoteca.api.repository.MesaRepository;
import com.ludoteca.api.repository.ReservaRepository;
import com.ludoteca.api.repository.TurnoDiaRepository;
import com.ludoteca.api.repository.UsuarioRepository;
import com.ludoteca.api.specification.ReservaSpecifications;
import com.ludoteca.api.utils.UsuarioPrincipal;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new MesaNoEncontradaException("Mesa no encontrada"));

        TurnoDia turnoDia = turnoDiaRepository.findById(dto.getTurnoDiaId())
                .orElseThrow(() -> new TurnoNoEncontradoException("Turno no encontrado"));

        if (reservaRepository.existsByMesaAndTurnoDia(mesa, turnoDia)) {
            throw new MesaReservadaException("La mesa ya está reservada en ese turno");
        }
        Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioPrincipal.getUsuario().getEmail());
        if (!usuario.isPresent()) {
            throw new UsuarioNoEncontradoException("Usuario no encontrado");
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
                .orElseThrow(() -> new ReservaNoEncontradaException("Reserva no encontrada"));

        reserva.setEstado(EstadoReserva.CANCELADO);
        reservaRepository.save(reserva);
    }

    public List<ReservaResponseDto> listarReservas(final String nombreUsuario, final Integer numeroMesa,
                                                   final LocalDate fechaTurno, final DiaSemana diaSemana) {

        var spec = ReservaSpecifications.conFiltros(
                nombreUsuario,
                numeroMesa,
                fechaTurno,
                diaSemana
        );
        var reservas = reservaRepository.findAll(spec);
        return reservaMapper.toList(reservas);
    }

    public DisponibilidadTurnoResponseDto obtenerDisponibilidad(LocalDate fecha, Long turnoDiaId) {

        TurnoDia turnoDia = turnoDiaRepository.findById(turnoDiaId)
                .orElseThrow(() -> new TurnoNoEncontradoException("TurnoDia no encontrado: " + turnoDiaId));

        // Validación para tu endpoint: si pasan fecha + turnoDiaId, confirmamos que coincidan
        if (!turnoDia.getFecha().equals(fecha)) {
            throw new FechaInvalidaException(
                    "La fecha enviada (" + fecha + ") no coincide con la fecha del TurnoDia (" + turnoDia.getFecha() + ")"
            );
        }

        // Traigo todas las mesas
        List<Mesa> mesas = mesaRepository.findAll();

        // Traigo reservas del turno y armo set de mesas reservadas
        List<Reserva> reservas = reservaRepository.findByTurnoDia_Id(turnoDiaId);

        Set<Long> mesasReservadasIds = reservas.stream()
                .map(r -> r.getMesa().getId())
                .collect(Collectors.toSet());

        List<MesaDisponibilidadDto> resultado = mesas.stream()
                .map(m -> MesaDisponibilidadDto.builder()
                        .id(m.getId())
                        .numero(m.getNumero())
                        .capacidad(m.getCapacidad())
                        .disponible(!mesasReservadasIds.contains(m.getId()))
                        .build()
                )
                .collect(Collectors.toList());

        return DisponibilidadTurnoResponseDto.builder()
                .turnoDiaId(turnoDiaId)
                .fecha(fecha)
                .mesas(resultado)
                .build();
    }
}
