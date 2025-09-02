package com.ludoteca.api.controller;

import com.ludoteca.api.dto.request.TurnoDiaRequestDto;
import com.ludoteca.api.dto.request.TurnoHorarioRequestDto;
import com.ludoteca.api.dto.response.TurnoDiaResponseDto;
import com.ludoteca.api.dto.response.TurnoHorarioResponseDto;
import com.ludoteca.api.enums.DiaSemana;
import com.ludoteca.api.service.TurnoDiaService;
import com.ludoteca.api.service.TurnoHorarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/turnos")
@RequiredArgsConstructor
@Validated
public class TurnoController {

    private final TurnoHorarioService turnoHorarioService;
    private final TurnoDiaService turnoDiaService;

    //  Obtener todos los turnos horarios
    @GetMapping("/horarios")
    public ResponseEntity<List<TurnoHorarioResponseDto>> obtenerHorarios() {
        return ResponseEntity.ok(turnoHorarioService.listarTodos());
    }

    // Obtener todos los turnos de una fecha
    @GetMapping("/dias")
    public ResponseEntity<List<TurnoDiaResponseDto>> obtenerTurnosDia(
            @RequestParam(name = "fecha", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate fecha,
            @RequestParam(name = "diaSemana", required = false) final DiaSemana diaSemana) {
        return ResponseEntity.ok(turnoDiaService.obtenerTurnosDia(fecha, diaSemana));
    }

    // Crear un nuevo turno horario (solo ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/horarios")
    public ResponseEntity<TurnoHorarioResponseDto> crearTurnoHorario(
            @Valid @RequestBody TurnoHorarioRequestDto dto) {
        TurnoHorarioResponseDto response = turnoHorarioService.crearTurnoHorario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //  Crear turno concreto en un día (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/dias")
    public ResponseEntity<TurnoDiaResponseDto> crearTurnoDia(
            @Valid @RequestBody TurnoDiaRequestDto dto) {
        TurnoDiaResponseDto response = turnoDiaService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Eliminar turno horario (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/horarios/{id}")
    public ResponseEntity<Void> eliminarTurnoHorario(@PathVariable final Long id) {
        turnoHorarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Eliminar turno día (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/dias/{id}")
    public ResponseEntity<Void> eliminarTurnoDia(@PathVariable final Long id) {
        turnoDiaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
