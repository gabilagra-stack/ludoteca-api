package com.ludoteca.api.specification;

import com.ludoteca.api.enums.DiaSemana;
import com.ludoteca.api.enums.EstadoReserva;
import com.ludoteca.api.model.Reserva;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public final class ReservaSpecifications {

    private ReservaSpecifications() {}

    public static Specification<Reserva> conFiltros(
            String nombreUsuario,
            Integer numeroMesa,
            LocalDate fecha,
            DiaSemana diaSemana
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // joins (solo si se necesitan)
            if (nombreUsuario != null && !nombreUsuario.isBlank()) {
                predicates.add(cb.equal(
                        root.get("usuario").get("nombre"),
                        nombreUsuario.trim()
                ));
            }

            if (numeroMesa != null) {
                predicates.add(cb.equal(
                        root.get("mesa").get("numero"),
                        numeroMesa
                ));
            }

            if (fecha != null) {
                predicates.add(cb.equal(
                        root.get("turnoDia").get("fecha"),
                        fecha
                ));
            }

            if (diaSemana != null) {
                predicates.add(cb.equal(
                        root.get("turnoDia").get("diaSemana"),
                        diaSemana
                ));
            }

            // Orden: por fecha de turno desc (y opcionalmente por horario si querés)
            query.orderBy(cb.desc(root.get("turnoDia").get("fecha")));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
