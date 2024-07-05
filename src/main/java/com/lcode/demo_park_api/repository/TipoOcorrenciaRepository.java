package com.lcode.demo_park_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lcode.demo_park_api.entity.TipoOcorrencia;

public interface TipoOcorrenciaRepository extends JpaRepository<TipoOcorrencia, Long> {

}
