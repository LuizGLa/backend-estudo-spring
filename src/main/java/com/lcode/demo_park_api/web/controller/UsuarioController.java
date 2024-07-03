package com.lcode.demo_park_api.web.controller;

import com.lcode.demo_park_api.entity.Usuario;
// import com.lcode.demo_park_api.repository.UsuarioRepository;
import com.lcode.demo_park_api.service.UsuarioService;
import com.lcode.demo_park_api.web.dto.UsuarioCreateDto;
import com.lcode.demo_park_api.web.dto.UsuarioResponseDto;
import com.lcode.demo_park_api.web.dto.UsuarioSenhaDto;
import com.lcode.demo_park_api.web.dto.mapper.UsuarioMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/usuarios")
@Tag(name = "Usuários", description = "Gestão de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Cadastrar um novo usuário")
    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody UsuarioCreateDto createDto) {
        Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
    }

    @Operation(summary = "Buscar um usuário por ID")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id) {
        Usuario user = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toDto(user));
    }

    @Operation(summary = "Buscar todos os usuários")
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> getAllUsers() {
        List<Usuario> users = usuarioService.buscarTodos();
        return ResponseEntity.ok(UsuarioMapper.toListDto(users));
    }

    @Operation(summary = "Atualizar a senha de um usuário")
    @PatchMapping("editar-senha/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UsuarioSenhaDto dto) {
        Usuario user = usuarioService.editarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
