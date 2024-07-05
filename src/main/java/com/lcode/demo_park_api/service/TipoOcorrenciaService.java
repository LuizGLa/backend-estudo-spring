package com.lcode.demo_park_api.service;

import org.springframework.stereotype.Service;

import com.lcode.demo_park_api.entity.TipoOcorrencia;
import com.lcode.demo_park_api.exception.EntityNotFoundException;
import com.lcode.demo_park_api.exception.RuaUniquesException;
import com.lcode.demo_park_api.repository.TipoOcorrenciaRepository;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TipoOcorrenciaService {

    private final TipoOcorrenciaRepository tipoOcorrenciaRepository;

    @Transactional
    public TipoOcorrencia salvar(TipoOcorrencia tipoOcorrencia) {
        try {
            return tipoOcorrenciaRepository.save(tipoOcorrencia);
        } catch (DataIntegrityViolationException ex) {
            throw new RuaUniquesException(
                    String.format("Tipo de Ocorrência '%s' já cadastrada", tipoOcorrencia.getDescricao()));
        }
    }

    @Transactional(readOnly = true)
    public TipoOcorrencia buscarPorId(Long id) {
        return tipoOcorrenciaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Rua id=%s não encontrado", id)));
    }

    @Transactional(readOnly = true)
    public List<TipoOcorrencia> buscarTodos() {
        return tipoOcorrenciaRepository.findAll();
    }

    @Transactional
    public void deletar(Long id) {
        tipoOcorrenciaRepository.deleteById(id);
    }

    @Transactional
    public TipoOcorrencia atualizar(Long id, TipoOcorrencia tipoOcorrenciaAtualizado) {
        TipoOcorrencia tipoOcorrenciaExistente = tipoOcorrenciaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Rua id=%s não encontrado", id)));
        tipoOcorrenciaExistente.setDescricao(tipoOcorrenciaAtualizado.getDescricao());

        return tipoOcorrenciaRepository.save(tipoOcorrenciaExistente);
    }

}
