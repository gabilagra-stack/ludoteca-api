package com.ludoteca.api.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public final class DestacadoSpecifications {

    private DestacadoSpecifications() {}

    public static <T> Specification<T> conTitulo(String titulo) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (titulo != null && !titulo.isBlank()) {
                predicates.add(cb.like(
                        cb.lower(root.get("titulo")),
                        "%" + titulo.trim().toLowerCase() + "%"
                ));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
