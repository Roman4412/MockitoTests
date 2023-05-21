package com.example.mockitotests.advice;

import com.example.mockitotests.exceptions.EmployeeAlreadyAddedException;
import com.example.mockitotests.exceptions.EmployeeNotFoundException;
import com.example.mockitotests.exceptions.InvalidDepartmentException;
import com.example.mockitotests.exceptions.InvalidInputException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(EmployeeAlreadyAddedException.class)
    public String employeeAlreadyAdded() {
        return new EmployeeAlreadyAddedException().getMessage();
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public String employeeNotFound() {
        return new EmployeeNotFoundException().getMessage();
    }

    @ExceptionHandler(InvalidInputException.class)
    public String invalidInput() {
        return new InvalidInputException().getMessage();
    }

    @ExceptionHandler(InvalidDepartmentException.class)
    public String invalidDepartment() {
        return new InvalidDepartmentException().getMessage();
    }
}
