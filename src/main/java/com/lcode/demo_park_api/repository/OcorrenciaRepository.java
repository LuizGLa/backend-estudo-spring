package com.lcode.demo_park_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lcode.demo_park_api.entity.Ocorrencia;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {

}
