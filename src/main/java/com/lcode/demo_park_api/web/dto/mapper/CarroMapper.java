package com.lcode.demo_park_api.web.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.lcode.demo_park_api.entity.Carro;
import com.lcode.demo_park_api.web.dto.CarroCreateDto;
import com.lcode.demo_park_api.web.dto.CarroResponseDto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CarroMapper {
    public static Carro toCarro(CarroCreateDto dto) {
        return new ModelMapper().map(dto, Carro.class);
    }

    public static CarroResponseDto toDto(Carro carro) {
        return new ModelMapper().map(carro, CarroResponseDto.class);
    }

    public static List<CarroResponseDto> toListDto(List<Carro> carros) {
        return carros.stream().map(carro -> toDto(carro)).collect(Collectors.toList());
    }
}
