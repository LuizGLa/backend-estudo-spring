package com.lcode.demo_park_api.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lcode.demo_park_api.repository.OcorrenciaRepository;
import com.lcode.demo_park_api.repository.RuaRepository;
import com.lcode.demo_park_api.repository.TipoOcorrenciaRepository;
import com.lcode.demo_park_api.repository.UsuarioRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private RuaRepository ruaRepository;

    @Autowired
    private TipoOcorrenciaRepository tipoOcorrenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/counts")
    public ResponseEntity<Map<String, Long>> getCounts() {
        Map<String, Long> counts = new HashMap<>();
        counts.put("ocorrencias", ocorrenciaRepository.count());
        counts.put("ruas", ruaRepository.count());
        counts.put("tiposOcorrencias", tipoOcorrenciaRepository.count());
        counts.put("usuarios", usuarioRepository.count());

        return ResponseEntity.ok(counts);
    }
}
