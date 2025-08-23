package com.ludoteca.api.service;

import com.ludoteca.api.dto.request.TurnoDiaRequestDto;
import com.ludoteca.api.dto.response.TurnoDiaResponseDto;
import com.ludoteca.api.model.TurnoDia;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TurnoDiaService {

    private final TurnoDiaRepository turnoDiaRepository;

    public List<TurnoDiaResponseDto> listar() {
        return turnoDiaRepository.findAll()
                .stream()
                .map(td -> new TurnoDiaResponseDto(td.getId(), td.getNombreDia()))
                .collect(Collectors.toList());
    }

    public TurnoDiaResponseDto crear(TurnoDiaRequestDto dto) {
        TurnoDia turnoDia = TurnoDia.builder()
                .nombreDia(dto.getNombreDia())
                .build();
        TurnoDia guardado = turnoDiaRepository.save(turnoDia);
        return new TurnoDiaResponseDto(guardado.getId(), guardado.getNombreDia());
    }

    public void eliminar(Long id) {
        if (!turnoDiaRepository.existsById(id)) {
            throw new RuntimeException("Turno día no encontrado");
        }
        turnoDiaRepository.deleteById(id);
    }
}
