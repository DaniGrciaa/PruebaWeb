package com.demo.springmio.repository;

import com.demo.springmio.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // buscar un usuario por username (útil para login)
    Optional<User> findByUsername(String username);

    // por si quieres validar que no se repitan correos
    Optional<User> findByEmail(String email);

    // comprobar si ya existe un username antes de crear otro igual
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
