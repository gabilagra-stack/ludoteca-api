package com.ludoteca.api.model;

import com.ludoteca.api.enums.Dificultad;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "juego_para_jugar")
public class JuegoParaJugar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "cantidad_disponible", nullable = false)
    private Integer cantidadDisponible;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(name = "numero_maximo")
    private Integer numeroMaximo;

    @Enumerated(EnumType.STRING)
    private Dificultad dificultad;

    private String categoria;

    @Column(name = "duracion_aproximada")
    private String duracionAproximada;
}
