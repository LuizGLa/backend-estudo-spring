package com.lcode.demo_park_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lcode.demo_park_api.entity.Rua;

import jakarta.transaction.Transactional;

public interface RuaRepository extends JpaRepository<Rua, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Ocorrencia o WHERE o.rua.id = :ruaId")
    void deleteOcorrenciasByRuaId(Long ruaId);

}
