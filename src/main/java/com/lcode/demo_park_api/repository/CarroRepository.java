package com.lcode.demo_park_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lcode.demo_park_api.entity.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    // Optional<Carro> findById(Long id);
}
