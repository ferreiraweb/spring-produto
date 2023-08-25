package com.spring.produto.exceptions;

public class CategoriaNomeMustBeNotRepeatedException extends RuntimeException {

    private static final Long serialVersionUID = 1L;

    public CategoriaNomeMustBeNotRepeatedException(String message) {
        super(message);
    }

}
