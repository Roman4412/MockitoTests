package com.example.mockitotests.exceptions;

public class InvalidDepartmentException extends RuntimeException{
    public InvalidDepartmentException() {
        super("Invalid department");
    }
}
