package com.ludoteca.api.controller;

import com.ludoteca.api.dto.request.JuegoParaJugarRequestDto;
import com.ludoteca.api.dto.response.JuegoParaJugarResponseDto;
import com.ludoteca.api.service.JuegosParaJugarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/juegos/jugar")
@RequiredArgsConstructor
public class JuegoParaJugarController {

    private final JuegosParaJugarService juegoParaJugarService;

    // Listar todos los juegos para jugar
    @GetMapping
    public ResponseEntity<List<JuegoParaJugarResponseDto>> buscarJuegos(
            @RequestParam(name = "nombre", required = false) final String nombre,
            @RequestParam(name = "categoria", required = false) final String categoria,
            @RequestParam(name = "dificultad", required = false) final String dificultad,
            @RequestParam(name = "jugadoresMax", required = false) final Integer jugadoresMax
    ) {
        List<JuegoParaJugarResponseDto> juegos = juegoParaJugarService.buscarPorFiltros(nombre, categoria,
                jugadoresMax, dificultad);
        return ResponseEntity.ok(juegos);
    }

    // Crear un nuevo juego para jugar (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<JuegoParaJugarResponseDto> crearJuego(@Valid @RequestBody final JuegoParaJugarRequestDto dto) {
        JuegoParaJugarResponseDto nuevo = juegoParaJugarService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // Eliminar un juego por ID (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarJuego(@PathVariable final Long id) {
        juegoParaJugarService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
