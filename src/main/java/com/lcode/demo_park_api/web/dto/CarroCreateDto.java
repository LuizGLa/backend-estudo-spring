package com.lcode.demo_park_api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarroCreateDto {
    @NotBlank
    private String nome;

    @NotBlank
    private String marca;

    @NotBlank
    private String placa;

    @NotBlank
    @Pattern(regexp = "ATIVO|INATIVO")
    private String status;

}