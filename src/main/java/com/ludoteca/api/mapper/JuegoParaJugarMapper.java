package com.ludoteca.api.mapper;

import com.ludoteca.api.dto.request.JuegoParaJugarRequestDto;
import com.ludoteca.api.dto.response.JuegoParaJugarResponseDto;
import com.ludoteca.api.model.JuegoParaJugar;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JuegoParaJugarMapper {

    JuegoParaJugar toEntity(JuegoParaJugarRequestDto dto);

    JuegoParaJugarResponseDto toDto(JuegoParaJugar juego);

    List<JuegoParaJugarResponseDto> toListDto(List<JuegoParaJugar> juegos);
}
