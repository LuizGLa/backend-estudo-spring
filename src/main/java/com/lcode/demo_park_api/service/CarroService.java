package com.lcode.demo_park_api.service;

import org.springframework.stereotype.Service;

import com.lcode.demo_park_api.entity.Carro;
import com.lcode.demo_park_api.exception.EntityNotFoundException;
import com.lcode.demo_park_api.exception.PlacaUniquesException;
import com.lcode.demo_park_api.repository.CarroRepository;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CarroService {

    private final CarroRepository carroRepository;

    @Transactional
    public Carro salvar(Carro carro) {
        try {
            return carroRepository.save(carro);
        } catch (DataIntegrityViolationException ex) {
            throw new PlacaUniquesException(String.format("Placa '%s' já cadastrada", carro.getPlaca()));

        }
    }

    @Transactional(readOnly = true)
    public Carro buscarPorId(Long id) {
        return carroRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Carro id=%s não encontrado", id)));
    }

    @Transactional(readOnly = true)
    public List<Carro> buscarTodos() {
        return carroRepository.findAll();
    }

    @Transactional
    public void deletar(Long id) {
        carroRepository.deleteById(id);
    }

    @Transactional
    public Carro atualizar(Long id, Carro carroAtualizado) {
        Carro carroExistente = carroRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Carro id=%s não encontrado", id)));
        carroExistente.setNome(carroAtualizado.getNome());
        carroExistente.setMarca(carroAtualizado.getMarca());
        carroExistente.setPlaca(carroAtualizado.getPlaca());
        carroExistente.setStatus(carroAtualizado.getStatus());

        return carroRepository.save(carroExistente);
    }

}
