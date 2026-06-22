package com.ludoteca.api.specification;

import com.ludoteca.api.model.Evento;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class EventoSpecifications {

    private EventoSpecifications() {}

    public static Specification<Evento> conFiltros(String titulo, LocalDate fechaDesde, LocalDate fechaHasta) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (titulo != null && !titulo.isBlank()) {
                predicates.add(cb.like(
                        cb.lower(root.get("titulo")),
                        "%" + titulo.trim().toLowerCase() + "%"
                ));
            }

            if (fechaDesde != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("fecha"), fechaDesde));
            }

            if (fechaHasta != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("fecha"), fechaHasta));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
