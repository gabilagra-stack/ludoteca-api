package com.ludoteca.api.mapper;

import com.ludoteca.api.dto.request.MesaRequestDto;
import com.ludoteca.api.dto.response.MesaResponseDto;
import com.ludoteca.api.dto.response.UsuarioResponseDto;
import com.ludoteca.api.model.Mesa;
import com.ludoteca.api.model.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MesaMapper {

    Mesa toEntity(MesaRequestDto dto);

    MesaResponseDto toDto(Mesa mesa);

    List<MesaResponseDto> toListDto(List<Mesa> mesas);
}
