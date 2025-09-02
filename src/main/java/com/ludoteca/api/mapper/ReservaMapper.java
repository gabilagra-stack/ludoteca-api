package com.ludoteca.api.mapper;

import com.ludoteca.api.dto.request.ReservaRequestDto;
import com.ludoteca.api.dto.response.ReservaResponseDto;
import com.ludoteca.api.model.Mesa;
import com.ludoteca.api.model.Reserva;
import com.ludoteca.api.model.TurnoDia;
import com.ludoteca.api.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "estado", constant = "RESERVADO")
    @Mapping(target = "usuario", source = "usuario")
    @Mapping(target = "mesa", source = "mesa")
    @Mapping(target = "turnoDia", source = "turnoDia")
    Reserva toEntity(ReservaRequestDto dto, Usuario usuario, Mesa mesa, TurnoDia turnoDia);

    @Mapping(target = "nombreUsuario", source = "usuario.nombre")
    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "numeroMesa", source = "mesa.numero")
    @Mapping(target = "mesaId", source = "mesa.id")
    @Mapping(target = "turnoDiaId", source = "turnoDia.id")
    @Mapping(target = "fechaTurno", source = "turnoDia.fecha")
    @Mapping(target = "horaInicio", source = "turnoDia.turnoHorario.horaInicio")
    @Mapping(target = "horaFin", source = "turnoDia.turnoHorario.horaFin")
    @Mapping(target = "diaSemana", expression = "java(obtenerDiaSemana(reserva.getTurnoDia().getFecha()))")
    ReservaResponseDto toDto(Reserva reserva);

    List<ReservaResponseDto> toList(List<Reserva> reservas);

    default String obtenerDiaSemana(java.time.LocalDate fecha) {
        DayOfWeek dia = fecha.getDayOfWeek();
        return dia.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
    }
}
