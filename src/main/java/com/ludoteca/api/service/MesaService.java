package com.ludoteca.api.service;

import com.ludoteca.api.dto.request.MesaRequestDto;
import com.ludoteca.api.dto.response.MesaResponseDto;
import com.ludoteca.api.model.Mesa;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MesaService {

    private final MesaRepository mesaRepository;
    private final MesaMapper mesaMapper;

    public List<MesaResponseDto> listarMesas() {
        return mesaRepository.findAll().stream()
                .map(mesaMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public MesaResponseDto crearMesa(MesaRequestDto dto) {
        Mesa mesa = mesaMapper.toEntity(dto);
        Mesa guardada = mesaRepository.save(mesa);
        return mesaMapper.toResponseDto(guardada);
    }

    @Transactional
    public void eliminarMesa(Long id) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesa no encontrada"));
        mesaRepository.delete(mesa);
    }

    public List<MesaResponseDto> buscarPorFiltros(Integer capacidadMinima, Integer capacidadMaxima) {
        List<Mesa> mesas = mesaRepository.findByCapacidadBetween(
                capacidadMinima != null ? capacidadMinima : 0,
                capacidadMaxima != null ? capacidadMaxima : Integer.MAX_VALUE
        );

        return mesas.stream()
                .map(mesaMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
