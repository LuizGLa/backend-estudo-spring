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

import com.lcode.demo_park_api.entity.Rua;
import com.lcode.demo_park_api.service.RuaService;
import com.lcode.demo_park_api.web.dto.mapper.RuaMapper;
import com.lcode.demo_park_api.web.dto.rua.RuaCreateDto;
import com.lcode.demo_park_api.web.dto.rua.RuaResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "Ruas", description = "Gestão de Ruas")
@RequestMapping("api/v1/ruas")
public class RuaController {
    private final RuaService ruaService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Cadastrar uma nova rua")
    public ResponseEntity<RuaResponseDto> create(@RequestBody @Valid RuaCreateDto createDto) {
        Rua rua = ruaService.salvar(RuaMapper.toRua(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(RuaMapper.toDto(rua));
    }

    @Operation(summary = "Buscar uma por ID")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('CLIENTE')")
    public ResponseEntity<RuaResponseDto> getById(@PathVariable Long id) {
        Rua rua = ruaService.buscarPorId(id);
        return ResponseEntity.ok(RuaMapper.toDto(rua));
    }

    @Operation(summary = "Buscar todos as ruas")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<RuaResponseDto>> getAllRuas() {
        List<Rua> ruas = ruaService.buscarTodos();
        return ResponseEntity.ok(RuaMapper.toListDto(ruas));
    }

    @Operation(summary = "Deletar uma rua")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ruaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar uma rua")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<RuaResponseDto> update(@PathVariable Long id,
            @RequestBody @Valid RuaCreateDto updateDto) {
        Rua ruaAtualizada = ruaService.atualizar(id, RuaMapper.toRua(updateDto));
        return ResponseEntity.ok(RuaMapper.toDto(ruaAtualizada));
    }

}
