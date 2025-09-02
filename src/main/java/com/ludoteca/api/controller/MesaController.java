package com.ludoteca.api.controller;

import com.ludoteca.api.dto.request.MesaRequestDto;
import com.ludoteca.api.dto.response.MesaResponseDto;
import com.ludoteca.api.service.MesaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mesas")
@RequiredArgsConstructor
public class MesaController {

    private final MesaService mesaService;

    // Obtener todas las mesas
    @GetMapping
    public ResponseEntity<List<MesaResponseDto>> listarMesasPorFiltros(
            @RequestParam(name = "numero", required = false) final Integer numero,
            @RequestParam(name = "capacidad", required = false) final Integer capacidad) {
        return ResponseEntity.ok(mesaService.listarMesas(numero, capacidad));
    }

    // Crear una nueva mesa (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MesaResponseDto> crearMesa(@Valid @RequestBody final MesaRequestDto dto) {
        MesaResponseDto nuevaMesa = mesaService.crearMesa(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMesa);
    }

    // Eliminar una mesa por id (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMesa(@PathVariable final Long id) {
        mesaService.eliminarMesa(id);
        return ResponseEntity.noContent().build();
    }
}
