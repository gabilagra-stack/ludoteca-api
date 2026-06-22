package com.ludoteca.api.mapper;

import com.ludoteca.api.dto.request.EventoRequestDto;
import com.ludoteca.api.dto.response.EventoResponseDto;
import com.ludoteca.api.model.Evento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventoMapper {

    @Mapping(target = "id", ignore = true)
    Evento toEntity(EventoRequestDto dto);

    EventoResponseDto toDto(Evento evento);

    List<EventoResponseDto> toListDto(List<Evento> eventos);
}
