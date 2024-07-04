package com.lcode.demo_park_api.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lcode.demo_park_api.entity.Carro;
import com.lcode.demo_park_api.service.CarroService;
import com.lcode.demo_park_api.web.dto.CarroCreateDto;
import com.lcode.demo_park_api.web.dto.CarroResponseDto;
import com.lcode.demo_park_api.web.dto.mapper.CarroMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@Tag(name = "Carros", description = "Gestão de Carros")
@RequestMapping("api/v1/carros")
public class CarroController {
    private final CarroService carroService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Cadastrar um novo carro")
    public ResponseEntity<CarroResponseDto> create(@RequestBody @Valid CarroCreateDto createDto) {
        Carro carro = carroService.salvar(CarroMapper.toCarro(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(CarroMapper.toDto(carro));
    }

    @Operation(summary = "Buscar um carro por ID")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') OR (hasRole('CLIENTE') AND #id ==  authentication.principal.id)")
    public ResponseEntity<CarroResponseDto> getById(@PathVariable Long id) {
        Carro carro = carroService.buscarPorId(id);
        return ResponseEntity.ok(CarroMapper.toDto(carro));
    }

    @Operation(summary = "Buscar todos os carros")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<CarroResponseDto>> getAllUsers() {
        List<Carro> carros = carroService.buscarTodos();
        return ResponseEntity.ok(CarroMapper.toListDto(carros));
    }

    @Operation(summary = "Deletar um carro")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carroService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar um carro")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CarroResponseDto> update(@PathVariable Long id,
            @RequestBody @Valid CarroCreateDto updateDto) {
        Carro carroAtualizado = carroService.atualizar(id, CarroMapper.toCarro(updateDto));
        return ResponseEntity.ok(CarroMapper.toDto(carroAtualizado));
    }

}
