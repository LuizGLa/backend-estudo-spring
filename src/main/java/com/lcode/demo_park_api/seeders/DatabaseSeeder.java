package com.lcode.demo_park_api.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lcode.demo_park_api.entity.Usuario;
import com.lcode.demo_park_api.repository.UsuarioRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
            Usuario usuario1 = new Usuario();
            usuario1.setName("Admin");
            usuario1.setUsername("admin@mail.com");
            usuario1.setPassword("$2a$10$H8J4WMuQdaDT4/4hzLG34uf9dGgmWDWR5WnsGWEmRjPpOPoYmFJR6");
            usuario1.setRole(Usuario.Role.ROLE_ADMIN);

            usuarioRepository.save(usuario1);

            System.out.println("Usuarios adicionados com sucesso!");
        } else {
            System.out.println("Usuarios já existem no banco de dados.");
        }
    }
}
