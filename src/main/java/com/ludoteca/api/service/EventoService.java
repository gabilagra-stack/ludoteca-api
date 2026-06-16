package com.ludoteca.api.service;

import com.ludoteca.api.dto.request.EventoRequestDto;
import com.ludoteca.api.dto.response.EventoResponseDto;
import com.ludoteca.api.exception.EventoNoEncontradoException;
import com.ludoteca.api.mapper.EventoMapper;
import com.ludoteca.api.model.Evento;
import com.ludoteca.api.repository.EventoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;
    private final EventoMapper eventoMapper;

    @Transactional
    public EventoResponseDto crear(final EventoRequestDto dto) {
        Evento evento = eventoMapper.toEntity(dto);
        return eventoMapper.toDto(eventoRepository.save(evento));
    }

    @Transactional
    public void eliminar(final Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new EventoNoEncontradoException("Evento no encontrado"));
        eventoRepository.delete(evento);
    }

    public Page<EventoResponseDto> buscarPorFiltros(final String titulo, final LocalDate fechaDesde,
                                                    final LocalDate fechaHasta, final Pageable pageable) {
        return eventoRepository.busquedaPorFiltros(titulo, fechaDesde, fechaHasta, pageable)
                .map(eventoMapper::toDto);
    }

    public EventoResponseDto obtenerPorId(final Long id) {
        return eventoRepository.findById(id)
                .map(eventoMapper::toDto)
                .orElseThrow(() -> new EventoNoEncontradoException("Evento no encontrado"));
    }
}
