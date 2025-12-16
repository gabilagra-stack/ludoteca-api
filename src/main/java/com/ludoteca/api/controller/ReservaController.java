package com.ludoteca.api.controller;

import com.ludoteca.api.dto.request.ReservaRequestDto;
import com.ludoteca.api.dto.response.DisponibilidadTurnoResponseDto;
import com.ludoteca.api.dto.response.ReservaResponseDto;
import com.ludoteca.api.enums.DiaSemana;
import com.ludoteca.api.service.ReservaService;
import com.ludoteca.api.utils.UsuarioPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {
    private final ReservaService reservaService;

    // Crear una nueva reserva
    @PostMapping
    public ResponseEntity<ReservaResponseDto> crearReserva(@RequestBody @Valid ReservaRequestDto request,
                                                           @AuthenticationPrincipal final UsuarioPrincipal usuarioPrincipal) {
        ReservaResponseDto response = reservaService.crearReserva(request, usuarioPrincipal);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Cancelar una reserva
    @PreAuthorize("hasRole('ADMIN') or @reservaSecurityService.esDuenoDeReserva(#id, authentication)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable final Integer id) {
        reservaService.cancelarReserva(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener reservas del usuario actual
    @GetMapping("/mis-reservas")
    public ResponseEntity<List<ReservaResponseDto>> obtenerMisReservas(@AuthenticationPrincipal final UsuarioPrincipal usuarioPrincipal) {
        List<ReservaResponseDto> reservas = reservaService.listarMisReservas(usuarioPrincipal);
        return ResponseEntity.ok(reservas);
    }

    // Obtener todas las reservas (admin)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ReservaResponseDto>> obtenerTodasLasReservas(
            @RequestParam(name = "nombreUsuario", required = false) final String nombreUsuario,
            @RequestParam(name = "numeroMesa", required = false) final Integer numeroMesa,
            @RequestParam(name = "fechaTurno", required = false) final LocalDate fechaTurno,
            @RequestParam(name = "diaSemana", required = false) final DiaSemana diaSemana) {
        return ResponseEntity.ok(reservaService.listarReservas(nombreUsuario, numeroMesa, fechaTurno, diaSemana));
    }

    @GetMapping("/disponibilidad")
    public ResponseEntity<DisponibilidadTurnoResponseDto> obtenerDisponibilidad(
            @RequestParam("fecha") LocalDate fecha,
            @RequestParam("turnoDiaId") Integer turnoDiaId
    ) {
        return ResponseEntity.ok(reservaService.obtenerDisponibilidad(fecha, turnoDiaId));
    }
}
