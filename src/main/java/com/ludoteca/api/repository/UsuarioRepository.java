package com.ludoteca.api.repository;

import com.ludoteca.api.enums.RolUsuario;
import com.ludoteca.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM Usuario u "
            + "WHERE (:nombre is null or :nombre = u.nombre ) "
            + "AND (:email is null or :email = u.email ) "
            + "AND (:rolUsuario is null or :rolUsuario = u.rol ) "
            + "ORDER BY u.nombre DESC")
    List<Usuario> busquedaPorFiltros(String nombre, String email, RolUsuario rolUsuario);
}
