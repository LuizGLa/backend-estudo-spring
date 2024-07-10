package com.lcode.demo_park_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lcode.demo_park_api.entity.TipoOcorrencia;

import jakarta.transaction.Transactional;

public interface TipoOcorrenciaRepository extends JpaRepository<TipoOcorrencia, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Ocorrencia o WHERE o.tipoOcorrencia.id = :tipoOcorrenciaId")
    void deleteOcorrenciasByTipoOcorrenciaId(Long tipoOcorrenciaId);

}
