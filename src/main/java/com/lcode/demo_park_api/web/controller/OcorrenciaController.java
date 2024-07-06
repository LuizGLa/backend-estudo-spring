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

import com.lcode.demo_park_api.entity.Ocorrencia;
import com.lcode.demo_park_api.service.OcorrenciaService;
import com.lcode.demo_park_api.web.dto.mapper.OcorrenciaMapper;
import com.lcode.demo_park_api.web.dto.ocorrencia.OcorrenciaCreateDto;
import com.lcode.demo_park_api.web.dto.ocorrencia.OcorrenciaResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "Ocorrencias", description = "Gestão de Ocorrencias")
@RequestMapping("api/v1/ocorrencias")
public class OcorrenciaController {
    private final OcorrenciaService ocorrenciaService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Cadastrar uma nova ocorrencia")
    public ResponseEntity<OcorrenciaResponseDto> create(@RequestBody @Valid OcorrenciaCreateDto createDto) {
        Ocorrencia ocorrencia = ocorrenciaService.salvar(OcorrenciaMapper.toOcorrencia(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(OcorrenciaMapper.toDto(ocorrencia));
    }

    @Operation(summary = "Buscar uma por ID")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('CLIENTE')")
    public ResponseEntity<OcorrenciaResponseDto> getById(@PathVariable Long id) {
        Ocorrencia ocorrencia = ocorrenciaService.buscarPorId(id);
        return ResponseEntity.ok(OcorrenciaMapper.toDto(ocorrencia));
    }

    @Operation(summary = "Buscar todos as ocorrencias")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<OcorrenciaResponseDto>> getAllOcorrencias() {
        List<Ocorrencia> ocorrencias = ocorrenciaService.buscarTodos();
        return ResponseEntity.ok(OcorrenciaMapper.toListDto(ocorrencias));
    }

    @Operation(summary = "Deletar uma ocorrencia")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ocorrenciaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar uma ocorrencia")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<OcorrenciaResponseDto> update(@PathVariable Long id,
            @RequestBody @Valid OcorrenciaCreateDto updateDto) {
        Ocorrencia ocorrenciaAtualizada = ocorrenciaService.atualizar(id, OcorrenciaMapper.toOcorrencia(updateDto));
        return ResponseEntity.ok(OcorrenciaMapper.toDto(ocorrenciaAtualizada));
    }

}
