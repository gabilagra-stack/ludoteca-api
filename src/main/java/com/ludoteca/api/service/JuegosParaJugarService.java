package com.ludoteca.api.service;

import com.ludoteca.api.dto.request.JuegoParaJugarRequestDto;
import com.ludoteca.api.dto.response.JuegoParaJugarResponseDto;
import com.ludoteca.api.model.JuegoParaJugar;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JuegosParaJugarService {

    private final JuegoParaJugarRepository juegoRepository;
    private final JuegoParaJugarMapper juegoMapper;

    public List<JuegoParaJugarResponseDto> listar() {
        return juegoRepository.findAll().stream()
                .map(juegoMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public JuegoParaJugarResponseDto crear(JuegoParaJugarRequestDto dto) {
        JuegoParaJugar juego = juegoMapper.toEntity(dto);
        JuegoParaJugar guardado = juegoRepository.save(juego);
        return juegoMapper.toResponseDto(guardado);
    }

    @Transactional
    public void eliminar(Long id) {
        JuegoParaJugar juego = juegoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Juego no encontrado"));
        juegoRepository.delete(juego);
    }

    public List<JuegoParaJugarResponseDto> buscarPorFiltros(String nombre, String categoria, Integer jugadoresMin, Integer jugadoresMax) {
        List<JuegoParaJugar> juegos = juegoRepository.findAll().stream()
                .filter(juego ->
                        (nombre == null || juego.getNombre().toLowerCase().contains(nombre.toLowerCase())) &&
                                (categoria == null || juego.getCategoria().equalsIgnoreCase(categoria)) &&
                                (jugadoresMin == null || juego.getMinimoJugadores() >= jugadoresMin) &&
                                (jugadoresMax == null || juego.getMaximoJugadores() <= jugadoresMax)
                )
                .collect(Collectors.toList());

        return juegos.stream()
                .map(juegoMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
