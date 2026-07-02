package com.ludoteca.api.mapper;

import com.ludoteca.api.dto.request.JuegoDestacadoRequestDto;
import com.ludoteca.api.dto.response.JuegoDestacadoResponseDto;
import com.ludoteca.api.model.JuegoDestacado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JuegoDestacadoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    JuegoDestacado toEntity(JuegoDestacadoRequestDto dto);

    JuegoDestacadoResponseDto toDto(JuegoDestacado juegoDestacado);
}
