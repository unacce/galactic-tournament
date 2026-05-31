package com.galactic.tournament.exception;

public class BusinessException
        extends RuntimeException {

    public BusinessException(
            String message) {

        super(message);
    }
}