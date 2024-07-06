package com.lcode.demo_park_api.exception;

public class CampoFaltandoException extends RuntimeException {
    public CampoFaltandoException(String mensagem) {
        super(mensagem);
    }
}
