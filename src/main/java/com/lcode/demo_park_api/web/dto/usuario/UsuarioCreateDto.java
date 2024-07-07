package com.lcode.demo_park_api.web.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioCreateDto {

    private String name;

    @NotBlank
    @Email(regexp = ".+@.+\\..+", message = "O email deve ser válido")
    private String username;

    @NotBlank
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String password;

    @NotBlank
    private String role;
}
