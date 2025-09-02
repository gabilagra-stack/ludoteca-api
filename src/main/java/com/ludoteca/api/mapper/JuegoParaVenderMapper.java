package com.ludoteca.api.mapper;

import com.ludoteca.api.dto.request.JuegoParaVenderRequestDto;
import com.ludoteca.api.dto.response.JuegoParaVenderResponseDto;
import com.ludoteca.api.model.JuegoParaVender;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JuegoParaVenderMapper {

    JuegoParaVender toEntity(JuegoParaVenderRequestDto dto);

    JuegoParaVenderResponseDto toDto(JuegoParaVender entity);

    List<JuegoParaVenderResponseDto> toListDto(List<JuegoParaVender> entities);
}
