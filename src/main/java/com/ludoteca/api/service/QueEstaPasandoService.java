package com.ludoteca.api.service;

import com.ludoteca.api.dto.request.QueEstaPasandoRequestDto;
import com.ludoteca.api.dto.response.QueEstaPasandoResponseDto;
import com.ludoteca.api.exception.QueEstaPasandoNoEncontradoException;
import com.ludoteca.api.mapper.QueEstaPasandoMapper;
import com.ludoteca.api.model.QueEstaPasando;
import com.ludoteca.api.repository.QueEstaPasandoRepository;
import com.ludoteca.api.specification.DestacadoSpecifications;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QueEstaPasandoService {

    private final QueEstaPasandoRepository queEstaPasandoRepository;
    private final QueEstaPasandoMapper queEstaPasandoMapper;

    @Transactional
    public QueEstaPasandoResponseDto crear(final QueEstaPasandoRequestDto dto) {
        QueEstaPasando item = queEstaPasandoMapper.toEntity(dto);
        return queEstaPasandoMapper.toDto(queEstaPasandoRepository.save(item));
    }

    @Transactional
    public void eliminar(final Long id) {
        QueEstaPasando item = queEstaPasandoRepository.findById(id)
                .orElseThrow(() -> new QueEstaPasandoNoEncontradoException("Item no encontrado"));
        queEstaPasandoRepository.delete(item);
    }

    public Page<QueEstaPasandoResponseDto> buscarPorFiltros(final String titulo, final Pageable pageable) {
        return queEstaPasandoRepository.findAll(DestacadoSpecifications.conTitulo(titulo), pageable)
                .map(queEstaPasandoMapper::toDto);
    }

    public QueEstaPasandoResponseDto obtenerPorId(final Long id) {
        return queEstaPasandoRepository.findById(id)
                .map(queEstaPasandoMapper::toDto)
                .orElseThrow(() -> new QueEstaPasandoNoEncontradoException("Item no encontrado"));
    }
}
