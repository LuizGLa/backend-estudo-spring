package com.lcode.demo_park_api.web.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

import com.lcode.demo_park_api.entity.Usuario;
import com.lcode.demo_park_api.web.dto.UsuarioCreateDto;
import com.lcode.demo_park_api.web.dto.UsuarioResponseDto;

public class UsuarioMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.getConfiguration().setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE);
    }

    public static Usuario toUsuario(UsuarioCreateDto createDto) {
        return modelMapper.map(createDto, Usuario.class);
    }

    public static UsuarioResponseDto toDto(Usuario usuario) {
        UsuarioResponseDto responseDto = modelMapper.map(usuario, UsuarioResponseDto.class);
        responseDto.setRole(usuario.getRole().name().substring("ROLE_".length()));
        return responseDto;
    }

    public static List<UsuarioResponseDto> toDtoList(List<Usuario> usuarios) {
        return usuarios.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }
}
