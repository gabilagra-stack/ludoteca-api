package com.ludoteca.api.service;

import com.ludoteca.api.dto.request.JuegoDestacadoRequestDto;
import com.ludoteca.api.dto.response.JuegoDestacadoResponseDto;
import com.ludoteca.api.exception.JuegoDestacadoNoEncontradoException;
import com.ludoteca.api.mapper.JuegoDestacadoMapper;
import com.ludoteca.api.model.JuegoDestacado;
import com.ludoteca.api.repository.JuegoDestacadoRepository;
import com.ludoteca.api.specification.DestacadoSpecifications;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JuegoDestacadoService {

    private final JuegoDestacadoRepository juegoDestacadoRepository;
    private final JuegoDestacadoMapper juegoDestacadoMapper;

    @Transactional
    public JuegoDestacadoResponseDto crear(final JuegoDestacadoRequestDto dto) {
        JuegoDestacado item = juegoDestacadoMapper.toEntity(dto);
        return juegoDestacadoMapper.toDto(juegoDestacadoRepository.save(item));
    }

    @Transactional
    public void eliminar(final Long id) {
        JuegoDestacado item = juegoDestacadoRepository.findById(id)
                .orElseThrow(() -> new JuegoDestacadoNoEncontradoException("Juego destacado no encontrado"));
        juegoDestacadoRepository.delete(item);
    }

    public Page<JuegoDestacadoResponseDto> buscarPorFiltros(final String titulo, final Pageable pageable) {
        return juegoDestacadoRepository.findAll(DestacadoSpecifications.conTitulo(titulo), pageable)
                .map(juegoDestacadoMapper::toDto);
    }

    public JuegoDestacadoResponseDto obtenerPorId(final Long id) {
        return juegoDestacadoRepository.findById(id)
                .map(juegoDestacadoMapper::toDto)
                .orElseThrow(() -> new JuegoDestacadoNoEncontradoException("Juego destacado no encontrado"));
    }
}
