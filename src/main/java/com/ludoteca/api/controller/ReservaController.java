package com.ludoteca.api.controller;

import com.ludoteca.api.dto.request.ReservaRequestDto;
import com.ludoteca.api.dto.response.ReservaResponseDto;
import com.ludoteca.api.model.Usuario;
import com.ludoteca.api.service.ReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
public class ReservaController {
    private final ReservaService reservaService;

    // Crear una nueva reserva
    @PostMapping
    public ResponseEntity<ReservaResponseDto> crearReserva(@RequestBody @Valid ReservaRequestDto request,
                                                           @AuthenticationPrincipal final Usuario usuario) {
        ReservaResponseDto response = reservaService.crearReserva(request, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Cancelar una reserva
    @PreAuthorize("hasRole('ADMIN') or @reservaSecurityService.esDueñoDeReserva(#id, authentication)")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable final Long id) {
        reservaService.cancelarReserva(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener reservas del usuario actual
    @GetMapping("/mis-reservas")
    public ResponseEntity<List<ReservaResponseDto>> obtenerMisReservas(@AuthenticationPrincipal final Usuario usuario) {
        List<ReservaResponseDto> reservas = reservaService.obtenerReservasDelUsuario(usuario);
        return ResponseEntity.ok(reservas);
    }

    // Obtener todas las reservas (admin)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ReservaResponseDto>> obtenerTodasLasReservas() {
        List<ReservaResponseDto> reservas = reservaService.obtenerTodasLasReservas();
        return ResponseEntity.ok(reservas);
    }
}
