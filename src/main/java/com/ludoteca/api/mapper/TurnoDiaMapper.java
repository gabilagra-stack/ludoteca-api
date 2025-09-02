package com.ludoteca.api.mapper;

import com.ludoteca.api.dto.request.TurnoDiaRequestDto;
import com.ludoteca.api.dto.response.TurnoDiaResponseDto;
import com.ludoteca.api.model.TurnoDia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TurnoDiaMapper {

    @Mapping(target = "turnoHorarioId", source = "turnoHorario.id")
    @Mapping(target = "horaInicio", source = "turnoHorario.horaInicio") // suponiendo que exista un campo horario
    @Mapping(target = "horaFin", source = "turnoHorario.horaFin") // suponiendo que exista un campo horario
    TurnoDiaResponseDto toDto(TurnoDia turnoDia);

    TurnoDia toEntity(TurnoDiaRequestDto turnoDiaRequestDto);

    List<TurnoDiaResponseDto> toListDto(List<TurnoDia> turnoDiaList);
}
