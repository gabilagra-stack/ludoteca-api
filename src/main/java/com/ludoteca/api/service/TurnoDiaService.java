package com.ludoteca.api.service;

import com.ludoteca.api.dto.request.TurnoDiaRequestDto;
import com.ludoteca.api.dto.response.TurnoDiaResponseDto;
import com.ludoteca.api.enums.DiaSemana;
import com.ludoteca.api.exception.TurnoNoEncontradoException;
import com.ludoteca.api.mapper.TurnoDiaMapper;
import com.ludoteca.api.model.TurnoDia;
import com.ludoteca.api.model.TurnoHorario;
import com.ludoteca.api.repository.TurnoDiaRepository;
import com.ludoteca.api.repository.TurnoHorarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnoDiaService {

    private final TurnoDiaRepository turnoDiaRepository;
    private final TurnoDiaMapper turnoDiaMapper;
    private final TurnoHorarioRepository turnoHorarioRepository;

    public List<TurnoDiaResponseDto> obtenerTurnosDia(final LocalDate fechaTurno, final DiaSemana diaSemana) {
        List<TurnoDia> lista;

        if (fechaTurno != null && diaSemana != null) {
            lista = turnoDiaRepository.findByFechaAndDiaSemanaOrderByFechaDesc(fechaTurno, diaSemana);
        } else if (fechaTurno != null) {
            lista = turnoDiaRepository.findByFechaOrderByFechaDesc(fechaTurno);
        } else if (diaSemana != null) {
            lista = turnoDiaRepository.findByDiaSemanaOrderByFechaDesc(diaSemana);
        } else {
            lista = turnoDiaRepository.findAllByOrderByFechaDesc();
        }

        return turnoDiaMapper.toListDto(lista);
    }

    public TurnoDiaResponseDto crear(TurnoDiaRequestDto dto) {
        TurnoDia turnoDia = turnoDiaMapper.toEntity(dto);
        TurnoHorario turnoHorario = turnoHorarioRepository.findById(dto.getTurnoHorarioId())
                .orElseThrow(() -> new TurnoNoEncontradoException("Turno de horario no encontrado con id: " +
                        dto.getTurnoHorarioId()));
        turnoDia.setTurnoHorario(turnoHorario);
        return turnoDiaMapper.toDto(turnoDiaRepository.save(turnoDia));
    }

    public void eliminar(Long id) {
        if (!turnoDiaRepository.existsById(id)) {
            throw new TurnoNoEncontradoException("Turno día no encontrado");
        }
        turnoDiaRepository.deleteById(id);
    }
}
