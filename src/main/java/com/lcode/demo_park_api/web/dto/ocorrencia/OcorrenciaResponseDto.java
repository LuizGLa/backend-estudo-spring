package com.lcode.demo_park_api.web.dto.ocorrencia;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lcode.demo_park_api.entity.Rua;
import com.lcode.demo_park_api.entity.TipoOcorrencia;
// import com.lcode.demo_park_api.entity.Usuario;
import com.lcode.demo_park_api.web.dto.usuario.UsuarioResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OcorrenciaResponseDto {

    private Long id;
    private String titulo;
    private String descricao;
    private String localizacao;
    private String latitude;
    private String longitude;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHora;

    private TipoOcorrencia tipoOcorrencia;
    private Rua rua;
    private UsuarioResponseDto usuario;

}
