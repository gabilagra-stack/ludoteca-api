package com.ludoteca.api.service;


import com.ludoteca.api.dto.request.JuegoParaVenderRequestDto;
import com.ludoteca.api.dto.response.JuegoParaVenderResponseDto;
import com.ludoteca.api.exception.JuegoNoEncontradoException;
import com.ludoteca.api.mapper.JuegoParaVenderMapper;
import com.ludoteca.api.model.JuegoParaVender;
import com.ludoteca.api.repository.JuegoParaVenderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JuegoParaVenderService {

    private final JuegoParaVenderRepository juegoParaVenderRepository;
    private final JuegoParaVenderMapper juegoVenderMapper;

    @Transactional
    public JuegoParaVenderResponseDto crearJuego(final JuegoParaVenderRequestDto dto) {
        JuegoParaVender juego = juegoVenderMapper.toEntity(dto);
        return juegoVenderMapper.toDto(juegoParaVenderRepository.save(juego));
    }

    @Transactional
    public void eliminarJuego(final Long id) {
        JuegoParaVender juego = juegoParaVenderRepository.findById(id)
                .orElseThrow(() -> new JuegoNoEncontradoException("Juego no encontrado"));
        juegoParaVenderRepository.delete(juego);
    }

    public List<JuegoParaVenderResponseDto> buscarPorFiltros(final String nombre, final String categoria,
                                                             final String dificultad, final Integer jugadoresMax,
                                                             final Integer stock) {
        return juegoVenderMapper.toListDto(juegoParaVenderRepository.busquedaPorFiltros(nombre, categoria,
                dificultad, jugadoresMax, stock));
    }
}
