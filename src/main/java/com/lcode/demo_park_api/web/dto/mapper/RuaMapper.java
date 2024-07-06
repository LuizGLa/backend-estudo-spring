package com.lcode.demo_park_api.web.dto.mapper;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

import com.lcode.demo_park_api.entity.Rua;
import com.lcode.demo_park_api.web.dto.rua.RuaCreateDto;
import com.lcode.demo_park_api.web.dto.rua.RuaResponseDto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RuaMapper {
    public static Rua toRua(RuaCreateDto dto) {
        return new ModelMapper().map(dto, Rua.class);
    }

    public static RuaResponseDto toDto(Rua rua) {
        return new ModelMapper().map(rua, RuaResponseDto.class);
    }

    public static List<RuaResponseDto> toListDto(List<Rua> ruas) {
        return ruas.stream().map(rua -> toDto(rua)).collect(Collectors.toList());
    }
}
