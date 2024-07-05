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

import com.lcode.demo_park_api.entity.TipoOcorrencia;
import com.lcode.demo_park_api.service.TipoOcorrenciaService;
import com.lcode.demo_park_api.web.dto.TipoOcorrenciaCreateDto;
import com.lcode.demo_park_api.web.dto.TipoOcorrenciaResponseDto;
import com.lcode.demo_park_api.web.dto.mapper.TipoOcorrenciaMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "Tipos de Ocorrência", description = "Gestão de Tipos de Ocorrência")
@RequestMapping("api/v1/tipos-ocorrencias")
public class TipoOcorrenciaController {
    private final TipoOcorrenciaService tipoOcorrenciaService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Cadastrar um novo tipo de ocorrência")
    public ResponseEntity<TipoOcorrenciaResponseDto> create(@RequestBody @Valid TipoOcorrenciaCreateDto createDto) {
        TipoOcorrencia tipoOcorrencia = tipoOcorrenciaService.salvar(TipoOcorrenciaMapper.toTipoOcorrencia(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(TipoOcorrenciaMapper.toDto(tipoOcorrencia));
    }

    @Operation(summary = "Buscar uma por ID")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') OR hasRole('CLIENTE')")
    public ResponseEntity<TipoOcorrenciaResponseDto> getById(@PathVariable Long id) {
        TipoOcorrencia tipoOcorrencia = tipoOcorrenciaService.buscarPorId(id);
        return ResponseEntity.ok(TipoOcorrenciaMapper.toDto(tipoOcorrencia));
    }

    @Operation(summary = "Buscar todos os tipos de ocorrência")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<TipoOcorrenciaResponseDto>> getAllTipos() {
        List<TipoOcorrencia> tipoOcorrencia = tipoOcorrenciaService.buscarTodos();
        return ResponseEntity.ok(TipoOcorrenciaMapper.toListDto(tipoOcorrencia));
    }

    @Operation(summary = "Deletar um tipo de ocorrência")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tipoOcorrenciaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar um tipo de ocorrência")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TipoOcorrenciaResponseDto> update(@PathVariable Long id,
            @RequestBody @Valid TipoOcorrenciaCreateDto updateDto) {
        TipoOcorrencia tipoOcorrenciaAtualizado = tipoOcorrenciaService.atualizar(id,
                TipoOcorrenciaMapper.toTipoOcorrencia(updateDto));
        return ResponseEntity.ok(TipoOcorrenciaMapper.toDto(tipoOcorrenciaAtualizado));
    }

}
