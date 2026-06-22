package com.ludoteca.api.controller;

import com.ludoteca.api.dto.request.EventoRequestDto;
import com.ludoteca.api.dto.response.EventoResponseDto;
import com.ludoteca.api.service.EventoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    @GetMapping
    public ResponseEntity<Page<EventoResponseDto>> buscarEventos(
            @RequestParam(name = "titulo", required = false) final String titulo,
            @RequestParam(name = "fechaDesde", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate fechaDesde,
            @RequestParam(name = "fechaHasta", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate fechaHasta,
            @PageableDefault(size = 10, sort = "fecha", direction = Sort.Direction.ASC) final Pageable pageable
    ) {
        return ResponseEntity.ok(eventoService.buscarPorFiltros(titulo, fechaDesde, fechaHasta, pageable));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<EventoResponseDto> obtenerEvento(@PathVariable final Long id) {
        return ResponseEntity.ok(eventoService.obtenerPorId(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EventoResponseDto> crearEvento(@Valid @RequestBody final EventoRequestDto dto) {
        EventoResponseDto nuevo = eventoService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable final Long id) {
        eventoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
