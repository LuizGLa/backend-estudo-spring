package com.lcode.demo_park_api.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RuaResponseDto {

    @NotBlank
    private Long id;

    @NotBlank
    private String nome;

}
