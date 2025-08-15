package com.ludoteca.api.model;

import com.ludoteca.api.enums.Dificultad;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "juego_para_vender")
public class JuegoParaVender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private BigDecimal precio;

    @Column(nullable = false)
    private Integer stock;

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
