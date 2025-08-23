package com.ludoteca.api.service;


import com.ludoteca.api.dto.response.JuegoParaVenderResponseDto;
import com.ludoteca.api.model.JuegoParaVender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JuegoParaVenderService {

    private final JuegoParaVenderRepository juegoParaVenderRepository;
    private final JuegoParaVenderMapper juegoMapper;

    public List<JuegoParaVenderResponseDto> listarTodos() {
        return juegoParaVenderRepository.findAll()
                .stream()
                .map(juegoMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<JuegoParaVenderResponseDto> buscarPorFiltros(String nombre, String categoria, Integer jugadoresMin, Integer jugadoresMax) {
        List<JuegoParaVender> juegos = juegoParaVenderRepository.buscarPorFiltros(nombre, categoria, jugadoresMin, jugadoresMax);
        return juegos.stream()
                .map(juegoMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
