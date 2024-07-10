package com.lcode.demo_park_api.web.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardCountsDto {
    private Long ocorrenciasCount;
    private Long ruasCount;
    private Long tiposOcorrenciasCount;
    private Long usuariosCount;
}
