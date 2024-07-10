package com.lcode.demo_park_api.seeders;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lcode.demo_park_api.entity.Ocorrencia;
import com.lcode.demo_park_api.entity.Rua;
import com.lcode.demo_park_api.entity.TipoOcorrencia;
import com.lcode.demo_park_api.entity.Usuario;
import com.lcode.demo_park_api.repository.OcorrenciaRepository;
import com.lcode.demo_park_api.repository.RuaRepository;
import com.lcode.demo_park_api.repository.TipoOcorrenciaRepository;
import com.lcode.demo_park_api.repository.UsuarioRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoOcorrenciaRepository tipoOcorrenciaRepository;

    @Autowired
    private RuaRepository ruaRepository;

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

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

        if (tipoOcorrenciaRepository.count() == 0) {
            TipoOcorrencia ocorrencia1 = new TipoOcorrencia();
            ocorrencia1.setDescricao("Acidente de Trânsito");
            ocorrencia1 = tipoOcorrenciaRepository.save(ocorrencia1);
            TipoOcorrencia ocorrencia2 = new TipoOcorrencia();
            ocorrencia2.setDescricao("Congestionamento");
            tipoOcorrenciaRepository.save(ocorrencia2);
            TipoOcorrencia ocorrencia3 = new TipoOcorrencia();
            ocorrencia3.setDescricao("Veículo Quebrado");
            tipoOcorrenciaRepository.save(ocorrencia3);

        }

        if (ruaRepository.count() == 0) {
            Rua rua1 = new Rua();
            rua1.setNome("Rua Jon Snow");
            ruaRepository.save(rua1);
            Rua rua2 = new Rua();
            rua2.setNome("Rua Da Gloria");
            ruaRepository.save(rua2);
            Rua rua3 = new Rua();
            rua3.setNome("Rua Do Castelo");
            ruaRepository.save(rua3);
        }

        if (ocorrenciaRepository.count() == 0) {
            Usuario usuario = usuarioRepository.findByUsername("admin@mail.com")
                    .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
            TipoOcorrencia tipoOcorrencia = tipoOcorrenciaRepository.findByDescricao("Acidente de Trânsito")
                    .orElseThrow(() -> new RuntimeException("TipoOcorrencia não encontrado"));
            Rua rua = ruaRepository.findByNome("Rua Jon Snow")
                    .orElseThrow(() -> new RuntimeException("Rua não encontrado"));

            Ocorrencia ocorrencia1 = new Ocorrencia();
            ocorrencia1.setTitulo("Acidente Grave");
            ocorrencia1.setDescricao("Acidente envolvendo múltiplos veículos");
            ocorrencia1.setLocalizacao("Centro da cidade");
            ocorrencia1.setDataHora(LocalDateTime.now());
            ocorrencia1.setLatitude("-14.847667644753423");
            ocorrencia1.setLongitude("-40.843526123279254");
            ocorrencia1.setTipoOcorrencia(tipoOcorrencia);
            ocorrencia1.setRua(rua);
            ocorrencia1.setUsuario(usuario);

            ocorrenciaRepository.save(ocorrencia1);

            System.out.println("Ocorrências adicionadas com sucesso!");
        } else {
            System.out.println("Ocorrências já existem no banco de dados.");
        }
    }
}
