package com.ludoteca.api.repository;

import com.ludoteca.api.model.JuegoDestacado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JuegoDestacadoRepository extends JpaRepository<JuegoDestacado, Long>,
        JpaSpecificationExecutor<JuegoDestacado> {
}
