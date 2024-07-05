package com.lcode.demo_park_api.web.dto.mapper;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

import com.lcode.demo_park_api.entity.TipoOcorrencia;
import com.lcode.demo_park_api.web.dto.TipoOcorrenciaCreateDto;
import com.lcode.demo_park_api.web.dto.TipoOcorrenciaResponseDto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TipoOcorrenciaMapper {
    public static TipoOcorrencia toTipoOcorrencia(TipoOcorrenciaCreateDto dto) {
        return new ModelMapper().map(dto, TipoOcorrencia.class);
    }

public static TipoOcorrenciaResponseDto toDto(TipoOcorrencia tipoOcorrencia) {
    return new ModelMapper().map(tipoOcorrencia, TipoOcorrenciaResponseDto.class);
}

public static List<TipoOcorrenciaResponseDto> toListDto(List<TipoOcorrencia> tipoOcorrencias) {
    return tipoOcorrencias.stream().map(tipoOcorrencia -> toDto(tipoOcorrencia)).collect(Collectors.toList());
}
}
