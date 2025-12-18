package com.ludoteca.api.service;

import com.ludoteca.api.dto.request.MesaRequestDto;
import com.ludoteca.api.dto.response.MesaResponseDto;
import com.ludoteca.api.exception.MesaNoEncontradaException;
import com.ludoteca.api.mapper.MesaMapper;
import com.ludoteca.api.model.Mesa;
import com.ludoteca.api.repository.MesaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MesaService {

    private final MesaRepository mesaRepository;
    private final MesaMapper mesaMapper;

    public List<MesaResponseDto> listarMesas(final Integer numero, final Integer capacidad) {
        return mesaMapper.toListDto(mesaRepository.busquedaPorFiltros(numero, capacidad));
    }

    @Transactional
    public MesaResponseDto crearMesa(MesaRequestDto dto) {
        Mesa mesa = mesaMapper.toEntity(dto);
        Mesa guardada = mesaRepository.save(mesa);
        return mesaMapper.toDto(guardada);
    }

    @Transactional
    public void eliminarMesa(Long id) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new MesaNoEncontradaException("Mesa no encontrada"));
        mesaRepository.delete(mesa);
    }
}
