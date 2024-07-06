package com.lcode.demo_park_api.web.dto.ocorrencia;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private String titulo;

    @NotBlank
    private String descricao;

    @NotBlank
    private String localizacao;

    @NotBlank
    private String latitude;

    @NotBlank
    private String longitude;

    @NotNull(message = "A data e hora não pode ser nula")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHora;

    @NotNull(message = "A ocorrencia não pode ser nula")
    private Long tipoOcorrencia;

    @NotNull(message = "A rua não pode ser nula")
    private Long rua;

    @NotNull(message = "O usuario não pode ser nulo")
    private Long usuario;

}
