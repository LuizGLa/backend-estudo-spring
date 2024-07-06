package com.lcode.demo_park_api.web.dto.ocorrencia;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OcorrenciaResponseDto {

    private String titulo;

    private String descricao;

    private String localizacao;

    private String latitude;

    private String longitude;

    private LocalDateTime dataHora;

    private Long tipoOcorrencia;

    private Long rua;

    private Long usuario;

}
