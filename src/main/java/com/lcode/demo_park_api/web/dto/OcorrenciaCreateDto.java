package com.lcode.demo_park_api.web.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OcorrenciaCreateDto {

    @NotBlank
    private Long id;

    @NotBlank
    private String tiulo;

    @NotBlank
    private String descricao;

    @NotBlank
    private String localizacao;

    @NotBlank
    private String latitude;

    @NotBlank
    private String longitude;
    @NotBlank

    @NotBlank
    private LocalDateTime data_hora;

    private Long tipo_ocorrencia_id;

    @NotBlank
    private Long rua_id;

}
