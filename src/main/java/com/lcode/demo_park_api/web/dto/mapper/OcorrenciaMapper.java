package com.lcode.demo_park_api.web.dto.mapper;

import org.modelmapper.ModelMapper;
import com.lcode.demo_park_api.entity.Ocorrencia;
import com.lcode.demo_park_api.entity.Rua;
import com.lcode.demo_park_api.entity.TipoOcorrencia;
import com.lcode.demo_park_api.entity.Usuario;
import com.lcode.demo_park_api.web.dto.ocorrencia.OcorrenciaCreateDto;
import com.lcode.demo_park_api.web.dto.ocorrencia.OcorrenciaResponseDto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OcorrenciaMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static Ocorrencia toOcorrencia(OcorrenciaCreateDto dto) {
        Ocorrencia ocorrencia = modelMapper.map(dto, Ocorrencia.class);

        TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();
        tipoOcorrencia.setId(dto.getTipoOcorrencia());
        ocorrencia.setTipoOcorrencia(tipoOcorrencia);

        Rua rua = new Rua();
        rua.setId(dto.getRua());
        ocorrencia.setRua(rua);

        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuario());
        ocorrencia.setUsuario(usuario);

        return ocorrencia;
    }

    public static OcorrenciaResponseDto toDto(Ocorrencia ocorrencia) {
        OcorrenciaResponseDto dto = modelMapper.map(ocorrencia, OcorrenciaResponseDto.class);
        dto.setTipoOcorrencia(ocorrencia.getTipoOcorrencia().getId());
        dto.setRua(ocorrencia.getRua().getId());
        dto.setUsuario(ocorrencia.getUsuario().getId());

        return dto;
    }

    public static List<OcorrenciaResponseDto> toListDto(List<Ocorrencia> ocorrencias) {
        return ocorrencias.stream().map(ocorrencia -> toDto(ocorrencia)).collect(Collectors.toList());
    }
}
