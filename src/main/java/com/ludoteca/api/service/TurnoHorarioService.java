package com.ludoteca.api.service;

import com.ludoteca.api.dto.request.TurnoHorarioRequestDto;
import com.ludoteca.api.dto.response.TurnoHorarioResponseDto;
import com.ludoteca.api.model.TurnoDia;
import com.ludoteca.api.model.TurnoHorario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TurnoHorarioService {

    private final TurnoHorarioRepository turnoHorarioRepository;
    private final TurnoDiaRepository turnoDiaRepository;
    private final TurnoHorarioMapper turnoHorarioMapper;

    public List<TurnoHorarioResponseDto> listarTodos() {
        return turnoHorarioRepository.findAll()
                .stream()
                .map(turnoHorarioMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public TurnoHorarioResponseDto crearTurnoHorario(TurnoHorarioRequestDto dto) {
        TurnoDia turnoDia = turnoDiaRepository.findById(dto.getIdTurnoDia())
                .orElseThrow(() -> new RuntimeException("Turno día no encontrado"));

        TurnoHorario turnoHorario = TurnoHorario.builder()
                .horaInicio(dto.getHoraInicio())
                .horaFin(dto.getHoraFin())
                .turnoDia(turnoDia)
                .build();

        TurnoHorario guardado = turnoHorarioRepository.save(turnoHorario);
        return turnoHorarioMapper.toResponseDto(guardado);
    }

    public void eliminar(Long id) {
        if (!turnoHorarioRepository.existsById(id)) {
            throw new RuntimeException("Turno horario no encontrado");
        }
        turnoHorarioRepository.deleteById(id);
    }
}
