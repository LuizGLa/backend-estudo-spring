package com.lcode.demo_park_api.repository;

import com.lcode.demo_park_api.entity.Usuario;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

    @Query("select u.role from Usuario u where u.username like :username")
    Usuario.Role findRoleByUsername(String username);

    @Modifying
    @Transactional
    @Query("DELETE FROM Ocorrencia o WHERE o.usuario.id = :usuarioId")
    void deleteOcorrenciasByUsuarioId(Long usuarioId);
}