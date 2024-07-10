package com.lcode.demo_park_api.service;

import org.springframework.stereotype.Service;

import com.lcode.demo_park_api.entity.Ocorrencia;
import com.lcode.demo_park_api.exception.CampoFaltandoException;
import com.lcode.demo_park_api.exception.EntityNotFoundException;
import com.lcode.demo_park_api.repository.OcorrenciaRepository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;

    @Transactional
    public Ocorrencia salvar(Ocorrencia ocorrencia) {
        try {
            return ocorrenciaRepository.save(ocorrencia);
        } catch (Exception ex) {
            throw new CampoFaltandoException(
                    "Campo obrigatório está faltando ou ocorreu outro erro ao salvar a ocorrência.");
        }
    }

    @Transactional(readOnly = true)
    public Ocorrencia buscarPorId(Long id) {
        return ocorrenciaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Ocorrencia id=%s não encontrado", id)));
    }


    @Transactional(readOnly = true)
    public List<Ocorrencia> buscarTodos() {
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findAll();
        return ocorrencias;
    }
    @Transactional
    public void deletar(Long id) {
        ocorrenciaRepository.deleteById(id);
    }

    @Transactional
    public Ocorrencia atualizar(Long id, Ocorrencia ocorrenciaAtualizado) {
        Ocorrencia ocorrenciaExistente = ocorrenciaRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Ocorrencia id=%s não encontrado", id)));
        ocorrenciaExistente.setTitulo(ocorrenciaAtualizado.getTitulo());
        ocorrenciaExistente.setDescricao(ocorrenciaAtualizado.getDescricao());
        ocorrenciaExistente.setLocalizacao(ocorrenciaAtualizado.getLocalizacao());
        ocorrenciaExistente.setTipoOcorrencia(ocorrenciaAtualizado.getTipoOcorrencia());
        ocorrenciaExistente.setRua(ocorrenciaAtualizado.getRua());
        ocorrenciaExistente.setDataHora(ocorrenciaAtualizado.getDataHora());
        ocorrenciaExistente.setUsuario(ocorrenciaAtualizado.getUsuario());
        ocorrenciaExistente.setLatitude(ocorrenciaAtualizado.getLatitude());
        ocorrenciaExistente.setLongitude(ocorrenciaAtualizado.getLongitude());
        return ocorrenciaRepository.save(ocorrenciaExistente);
    }

}
