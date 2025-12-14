package com.ludoteca.api.dto.response;

import com.ludoteca.api.dto.MesaDisponibilidadDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DisponibilidadTurnoResponseDto {
    private Integer turnoDiaId;
    private LocalDate fecha;
    private List<MesaDisponibilidadDto> mesas;
}
