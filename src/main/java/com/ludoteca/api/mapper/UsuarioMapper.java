package com.ludoteca.api.mapper;


import com.ludoteca.api.dto.request.CrearUsuarioDto;
import com.ludoteca.api.dto.response.UsuarioResponseDto;
import com.ludoteca.api.model.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {


    UsuarioResponseDto toDto(Usuario usuario);


    Usuario toEntity(CrearUsuarioDto usuarioDto);


    List<UsuarioResponseDto> toListDto(List<Usuario> usuarios);
}
