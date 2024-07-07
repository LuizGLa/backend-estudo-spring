package com.lcode.demo_park_api.web.dto.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
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

    static {
        modelMapper.addMappings(new PropertyMap<OcorrenciaCreateDto, Ocorrencia>() {
            @Override
            protected void configure() {
                map(source.getTipoOcorrencia(), destination.getTipoOcorrencia());
                map(source.getRua(), destination.getRua());
                map(source.getUsuario(), destination.getUsuario());
            }
        });

        modelMapper.addMappings(new PropertyMap<Ocorrencia, OcorrenciaResponseDto>() {
            @Override
            protected void configure() {
                map().setTipoOcorrencia(source.getTipoOcorrencia());
                map().setRua(source.getRua());
            }
        });
    }

    public static Ocorrencia toOcorrencia(OcorrenciaCreateDto dto) {
        Ocorrencia ocorrencia = modelMapper.map(dto, Ocorrencia.class);
        ocorrencia.setTipoOcorrencia(sourceToTipoOcorrencia(dto.getTipoOcorrencia()));
        ocorrencia.setRua(sourceToRua(dto.getRua()));
        ocorrencia.setUsuario(sourceToUsuario(dto.getUsuario()));
        return ocorrencia;
    }

    public static OcorrenciaResponseDto toDto(Ocorrencia ocorrencia) {
        return modelMapper.map(ocorrencia, OcorrenciaResponseDto.class);
    }

    public static List<OcorrenciaResponseDto> toListDto(List<Ocorrencia> ocorrencias) {
        return ocorrencias.stream().map(OcorrenciaMapper::toDto).collect(Collectors.toList());
    }

    private static TipoOcorrencia sourceToTipoOcorrencia(Long id) {
        if (id == null) {
            return null;
        }
        TipoOcorrencia tipoOcorrencia = new TipoOcorrencia();
        tipoOcorrencia.setId(id);
        return tipoOcorrencia;
    }

    private static Rua sourceToRua(Long id) {
        if (id == null) {
            return null;
        }
        Rua rua = new Rua();
        rua.setId(id);
        return rua;
    }

    private static Usuario sourceToUsuario(Long id) {
        if (id == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(id);
        return usuario;
    }
}
