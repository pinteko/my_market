package com.korsuk.my_market.exceptions;

public class ExistEntityException extends RuntimeException{

    public ExistEntityException(String message) {
        super(message);
    }
}
