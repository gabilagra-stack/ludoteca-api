package com.ludoteca.api.repository;

import com.ludoteca.api.enums.Dificultad;
import com.ludoteca.api.enums.RolUsuario;
import com.ludoteca.api.model.JuegoParaJugar;
import com.ludoteca.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JuegoParaJugarRepository extends JpaRepository<JuegoParaJugar, Long> {

    @Query("SELECT j FROM JuegoParaJugar j "
            + "WHERE (:nombre is null or :nombre = j.nombre ) "
            + "AND (:categoria is null or :categoria = j.categoria ) "
            + "AND (:jugadoresMax is null or :jugadoresMax = j.numeroMaximo ) "
            + "AND (:dificultad is null or :dificultad = j.dificultad )"
            + "ORDER BY j.nombre DESC")
    List<JuegoParaJugar> busquedaPorFiltros( String nombre, String categoria, Integer jugadoresMax, Dificultad dificultad);

}
