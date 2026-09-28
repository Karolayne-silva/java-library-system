package com.biblioteca.exceptions;

public class LimiteEmprestimoException extends RuntimeException{
    public LimiteEmprestimoException(String message){
        super(message);
    }
}