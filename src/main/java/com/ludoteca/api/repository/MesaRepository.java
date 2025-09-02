package com.ludoteca.api.repository;

import com.ludoteca.api.enums.RolUsuario;
import com.ludoteca.api.model.Mesa;
import com.ludoteca.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MesaRepository extends JpaRepository<Mesa, Long> {

    boolean existsByNumero(Integer numero);

    @Query("SELECT m FROM Mesa m "
            + "WHERE (:numero is null or :numero = m.numero ) "
            + "AND (:capacidad is null or :capacidad = m.capacidad ) "
            + "ORDER BY m.numero DESC")
    List<Mesa> busquedaPorFiltros(Integer numero, Integer capacidad);
}
