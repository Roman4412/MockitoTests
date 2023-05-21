package com.example.mockitotests.exceptions;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException() {
        super("Invalid input");
    }
}
