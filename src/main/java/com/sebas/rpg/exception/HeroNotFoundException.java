package com.sebas.rpg.exception;

public class HeroNotFoundException extends RuntimeException {

    public HeroNotFoundException(String message) {
        super(message);
    }
}