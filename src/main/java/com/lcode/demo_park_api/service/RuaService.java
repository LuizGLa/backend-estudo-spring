package com.lcode.demo_park_api.service;

import org.springframework.stereotype.Service;

import com.lcode.demo_park_api.entity.Rua;
import com.lcode.demo_park_api.exception.EntityNotFoundException;
import com.lcode.demo_park_api.exception.RuaUniquesException;
import com.lcode.demo_park_api.repository.RuaRepository;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RuaService {

    private final RuaRepository ruaRepository;

    @Transactional
    public Rua salvar(Rua rua) {
        try {
            return ruaRepository.save(rua);
        } catch (DataIntegrityViolationException ex) {
            throw new RuaUniquesException(String.format("Rua '%s' já cadastrada", rua.getNome()));

        }
    }

    @Transactional(readOnly = true)
    public Rua buscarPorId(Long id) {
        return ruaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Rua id=%s não encontrado", id)));
    }

    @Transactional(readOnly = true)
    public List<Rua> buscarTodos() {
        return ruaRepository.findAll();
    }

    @Transactional
    public void deletar(Long id) {
        ruaRepository.deleteById(id);
    }

    @Transactional
    public Rua atualizar(Long id, Rua ruaAtualizada) {
        Rua ruaExistente = ruaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Rua id=%s não encontrado", id)));
        ruaExistente.setNome(ruaAtualizada.getNome());

        return ruaRepository.save(ruaExistente);
    }

}
