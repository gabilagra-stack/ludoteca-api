package com.ludoteca.api.mapper;

import com.ludoteca.api.dto.request.TurnoHorarioRequestDto;
import com.ludoteca.api.dto.response.TurnoHorarioResponseDto;
import com.ludoteca.api.model.TurnoHorario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TurnoHorarioMapper {

    TurnoHorario toEntity(TurnoHorarioRequestDto dto);

    TurnoHorarioResponseDto toDto(TurnoHorario entity);

    List<TurnoHorarioResponseDto> toListDto(List<TurnoHorario> entities);
}
