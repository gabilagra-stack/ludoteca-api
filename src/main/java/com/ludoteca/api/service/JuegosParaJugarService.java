package com.ludoteca.api.service;

import com.ludoteca.api.dto.request.JuegoParaJugarRequestDto;
import com.ludoteca.api.dto.response.JuegoParaJugarResponseDto;
import com.ludoteca.api.enums.Dificultad;
import com.ludoteca.api.mapper.JuegoParaJugarMapper;
import com.ludoteca.api.model.JuegoParaJugar;
import com.ludoteca.api.repository.JuegoParaJugarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JuegosParaJugarService {

    private final JuegoParaJugarRepository juegoParaJugarRepository;
    private final JuegoParaJugarMapper juegoMapper;

    @Transactional
    public JuegoParaJugarResponseDto crear(final JuegoParaJugarRequestDto dto) {
        JuegoParaJugar juego = juegoMapper.toEntity(dto);
        return juegoMapper.toDto(juegoParaJugarRepository.save(juego));
    }

    @Transactional
    public void eliminar(final Long id) {
        JuegoParaJugar juego = juegoParaJugarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado"));
        juegoParaJugarRepository.delete(juego);
    }

    public List<JuegoParaJugarResponseDto> buscarPorFiltros(final String nombre, final String categoria,
                                                            final Integer jugadoresMax, final String dificultad) {
        Dificultad dificultadEnum = Dificultad.contieneEnum(dificultad);
        return juegoMapper.toListDto(juegoParaJugarRepository.busquedaPorFiltros(nombre, categoria, jugadoresMax,
                dificultadEnum));
    }
}
