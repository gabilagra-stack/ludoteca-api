package com.ludoteca.api.service;

import com.ludoteca.api.dto.request.TurnoHorarioRequestDto;
import com.ludoteca.api.dto.response.TurnoHorarioResponseDto;
import com.ludoteca.api.exception.TurnoNoEncontradoException;
import com.ludoteca.api.mapper.TurnoHorarioMapper;
import com.ludoteca.api.repository.TurnoHorarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnoHorarioService {

    private final TurnoHorarioRepository turnoHorarioRepository;
    private final TurnoHorarioMapper turnoHorarioMapper;

    public List<TurnoHorarioResponseDto> listarTodos() {
        return turnoHorarioMapper.toListDto(turnoHorarioRepository.findAll());
    }

    public TurnoHorarioResponseDto crearTurnoHorario(TurnoHorarioRequestDto dto) {
        return turnoHorarioMapper.toDto(turnoHorarioRepository.save(turnoHorarioMapper.toEntity(dto)));
    }

    public void eliminar(Long id) {
        if (!turnoHorarioRepository.existsById(id)) {
            throw new TurnoNoEncontradoException("Turno horario no encontrado");
        }
        turnoHorarioRepository.deleteById(id);
    }
}
