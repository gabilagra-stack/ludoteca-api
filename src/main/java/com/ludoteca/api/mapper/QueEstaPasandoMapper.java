package com.ludoteca.api.mapper;

import com.ludoteca.api.dto.request.QueEstaPasandoRequestDto;
import com.ludoteca.api.dto.response.QueEstaPasandoResponseDto;
import com.ludoteca.api.model.QueEstaPasando;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QueEstaPasandoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    QueEstaPasando toEntity(QueEstaPasandoRequestDto dto);

    QueEstaPasandoResponseDto toDto(QueEstaPasando queEstaPasando);
}
