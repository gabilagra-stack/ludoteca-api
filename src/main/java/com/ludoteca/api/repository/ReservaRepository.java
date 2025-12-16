package com.ludoteca.api.repository;

import com.ludoteca.api.enums.DiaSemana;
import com.ludoteca.api.enums.RolUsuario;
import com.ludoteca.api.model.Mesa;
import com.ludoteca.api.model.Reserva;
import com.ludoteca.api.model.TurnoDia;
import com.ludoteca.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Integer>, JpaSpecificationExecutor<Reserva> {

    List<Reserva> findByUsuarioId(Long usuarioId);

    boolean existsByMesaAndTurnoDia(Mesa mesa, TurnoDia turnoDia);

    List<Reserva> findListByUsuario(Usuario usuario);

    @Query("SELECT r FROM Reserva r "
            + "WHERE (:nombreUsuario is null or :nombreUsuario = r.usuario.nombre ) "
            + "AND (:numeroMesa is null or :numeroMesa = r.mesa.numero ) "
            + "AND (:fechaTurno is null or :fechaTurno = r.turnoDia.fecha ) "
            + "AND (:diaSemana is null or :diaSemana = r.turnoDia.diaSemana ) "
            + "ORDER BY r.turnoDia.fecha DESC")
    List<Reserva> busquedaPorFiltros(String nombreUsuario, Integer numeroMesa, LocalDate fechaTurno, DiaSemana diaSemana);

    List<Reserva> findByTurnoDia_Id(Integer turnoDiaId);

}
