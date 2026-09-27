package com.biblioteca.exceptions;

public class EmprestimoNaoEncontradoException extends RuntimeException{

    public EmprestimoNaoEncontradoException(String message) {
        super(message);
    }
}
