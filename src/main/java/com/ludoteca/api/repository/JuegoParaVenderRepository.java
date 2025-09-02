package com.ludoteca.api.repository;

import com.ludoteca.api.model.JuegoParaJugar;
import com.ludoteca.api.model.JuegoParaVender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JuegoParaVenderRepository extends JpaRepository<JuegoParaVender, Long> {


    @Query("SELECT j FROM JuegoParaVender j "
            + "WHERE (:nombre is null or :nombre = j.nombre ) "
            + "AND (:categoria is null or :categoria = j.categoria ) "
            + "AND (:jugadoresMax is null or :jugadoresMax = j.numeroMaximo ) "
            + "AND (:dificultad is null or :dificultad = j.dificultad ) "
            + "AND (:stock is null or :stock = j.stock ) "
            + "ORDER BY j.nombre DESC")
    List<JuegoParaVender> busquedaPorFiltros(String nombre, String categoria, String dificultad, Integer jugadoresMax,
                                             Integer stock);
}
