package com.lcode.demo_park_api.web.dto.usuario;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class UsuarioResponseDto {

    private Long id;
    private String name;
    private String username;
    private String role;

}
