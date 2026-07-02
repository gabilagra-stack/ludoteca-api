package com.ludoteca.api.repository;

import com.ludoteca.api.model.QueEstaPasando;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QueEstaPasandoRepository extends JpaRepository<QueEstaPasando, Long>,
        JpaSpecificationExecutor<QueEstaPasando> {
}
