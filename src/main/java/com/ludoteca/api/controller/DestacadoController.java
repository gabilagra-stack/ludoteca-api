package com.ludoteca.api.controller;

import com.ludoteca.api.dto.request.JuegoDestacadoRequestDto;
import com.ludoteca.api.dto.request.QueEstaPasandoRequestDto;
import com.ludoteca.api.dto.response.JuegoDestacadoResponseDto;
import com.ludoteca.api.dto.response.QueEstaPasandoResponseDto;
import com.ludoteca.api.service.JuegoDestacadoService;
import com.ludoteca.api.service.QueEstaPasandoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/destacados")
@RequiredArgsConstructor
public class DestacadoController {

    private final QueEstaPasandoService queEstaPasandoService;
    private final JuegoDestacadoService juegoDestacadoService;

    @GetMapping("/que-esta-pasando")
    public ResponseEntity<Page<QueEstaPasandoResponseDto>> buscarQueEstaPasando(
            @RequestParam(name = "titulo", required = false) final String titulo,
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.DESC)
            final Pageable pageable
    ) {
        return ResponseEntity.ok(queEstaPasandoService.buscarPorFiltros(titulo, pageable));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/que-esta-pasando/{id}")
    public ResponseEntity<QueEstaPasandoResponseDto> obtenerQueEstaPasando(@PathVariable final Long id) {
        return ResponseEntity.ok(queEstaPasandoService.obtenerPorId(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/que-esta-pasando")
    public ResponseEntity<QueEstaPasandoResponseDto> crearQueEstaPasando(
            @Valid @RequestBody final QueEstaPasandoRequestDto dto
    ) {
        QueEstaPasandoResponseDto nuevo = queEstaPasandoService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/que-esta-pasando/{id}")
    public ResponseEntity<Void> eliminarQueEstaPasando(@PathVariable final Long id) {
        queEstaPasandoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/juegos")
    public ResponseEntity<Page<JuegoDestacadoResponseDto>> buscarJuegosDestacados(
            @RequestParam(name = "titulo", required = false) final String titulo,
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.DESC)
            final Pageable pageable
    ) {
        return ResponseEntity.ok(juegoDestacadoService.buscarPorFiltros(titulo, pageable));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/juegos/{id}")
    public ResponseEntity<JuegoDestacadoResponseDto> obtenerJuegoDestacado(@PathVariable final Long id) {
        return ResponseEntity.ok(juegoDestacadoService.obtenerPorId(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/juegos")
    public ResponseEntity<JuegoDestacadoResponseDto> crearJuegoDestacado(
            @Valid @RequestBody final JuegoDestacadoRequestDto dto
    ) {
        JuegoDestacadoResponseDto nuevo = juegoDestacadoService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/juegos/{id}")
    public ResponseEntity<Void> eliminarJuegoDestacado(@PathVariable final Long id) {
        juegoDestacadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
