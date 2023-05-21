package com.example.mockitotests;


public class EmployeeServiceTestConstants {

    public static final String VALID_FIRSTNAME = "Рекорд";
    public static final String VALID_LASTNAME = "Надоев";
    public static final String VALID_KEY = "Рекорд Надоев";
    public static final String INVALID_FIRSTNAME = "Recor1d";
    public static final String INVALID_LASTNAME = "Nadoe3v";
    public static final Double SALARY = 120_000.00;
    public static final int DEPARTMENT = 1;
    public static final Employee DEFAULT_EMPLOYEE = new Employee(VALID_FIRSTNAME,VALID_LASTNAME,DEPARTMENT,SALARY);

    public static final String EMPLOYEE_ALREADY_ADDED_MESSAGE = "Employee already added";
    public static final String EMPLOYEE_NOT_FOUND_MESSAGE ="Employee not found";
    public static final String INVALID_INPUT_MESSAGE = "Invalid input";
}
