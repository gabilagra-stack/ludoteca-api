package com.ludoteca.api.model;

import com.ludoteca.api.enums.EstadoReserva;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "reserva", uniqueConstraints = @UniqueConstraint(columnNames = {"mesa_id", "turno_dia_id"}))
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoReserva estadoReserva;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "mesa_id", nullable = false)
    private Mesa mesa;

    @ManyToOne
    @JoinColumn(name = "turno_dia_id", nullable = false)
    private TurnoDia turno_dia;

}
