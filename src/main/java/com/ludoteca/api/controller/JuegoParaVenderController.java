package com.ludoteca.api.controller;

import com.ludoteca.api.dto.request.JuegoParaVenderRequestDto;
import com.ludoteca.api.dto.response.JuegoParaVenderResponseDto;
import com.ludoteca.api.service.JuegoParaVenderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/juegos/vender")
@RequiredArgsConstructor
public class JuegoParaVenderController {

    private final JuegoParaVenderService juegoParaVenderService;

    // Listar todos los juegos
    @GetMapping
    public ResponseEntity<List<JuegoParaVenderResponseDto>> buscarJuegos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String dificultad
    ) {
        List<JuegoParaVenderResponseDto> juegos = juegoService.buscar(nombre, categoria, dificultad);
        return ResponseEntity.ok(juegos);
    }

    // Crear un nuevo juego
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<JuegoParaVenderResponseDto> crearJuego(@Valid @RequestBody final JuegoParaVenderRequestDto dto) {
        JuegoParaVenderResponseDto nuevo = juegoParaVenderService.crearJuego(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    // Eliminar un juego por ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarJuego(@PathVariable final Long id) {
        juegoParaVenderService.eliminarJuego(id);
        return ResponseEntity.noContent().build();
    }
}
