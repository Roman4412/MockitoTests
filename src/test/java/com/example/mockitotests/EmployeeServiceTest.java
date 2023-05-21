package com.example.mockitotests;

import com.example.mockitotests.exceptions.EmployeeAlreadyAddedException;
import com.example.mockitotests.exceptions.EmployeeNotFoundException;
import com.example.mockitotests.exceptions.InvalidInputException;
import com.example.mockitotests.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static com.example.mockitotests.EmployeeServiceTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    public static EmployeeServiceImpl out;

    @BeforeEach
    public void before() {
        out = new EmployeeServiceImpl();

    }



    public static Stream<Arguments> paramsForAddTest() {
        return Stream.of(
                Arguments.of(VALID_FIRSTNAME,INVALID_LASTNAME,DEPARTMENT,SALARY),
                Arguments.of(INVALID_FIRSTNAME,VALID_LASTNAME,DEPARTMENT,SALARY),
                Arguments.of(INVALID_FIRSTNAME,INVALID_LASTNAME,DEPARTMENT,SALARY)
        );
    }

    @Test
    public void addTest() {
        Employee result = out.add(VALID_FIRSTNAME, VALID_LASTNAME, DEPARTMENT, SALARY);
        assertEquals(result, DEFAULT_EMPLOYEE);
    }
    @Test
    public void addWhenEmployeeAlreadyAddedTest() {
        out.add(VALID_FIRSTNAME,VALID_LASTNAME,DEPARTMENT,SALARY);
        EmployeeAlreadyAddedException thrown = assertThrows(
            EmployeeAlreadyAddedException.class,
            () -> out.add(VALID_FIRSTNAME, VALID_LASTNAME, DEPARTMENT, SALARY));
        assertEquals(thrown.getMessage(),EMPLOYEE_ALREADY_ADDED_MESSAGE);
    }

    @ParameterizedTest
    @MethodSource("paramsForAddTest")
    public void addWhenEmployeeInvalidTest(String firstName, String lastName, int department, double salary) {
        InvalidInputException thrown = assertThrows(
                InvalidInputException.class,
                () -> out.add(firstName, lastName, department, salary));
        assertEquals(thrown.getMessage(), INVALID_INPUT_MESSAGE);
    }
    @Test
    public void removeTest() {
        out.add(VALID_FIRSTNAME, VALID_LASTNAME, DEPARTMENT, SALARY);
        Employee result = out.remove(VALID_FIRSTNAME,VALID_LASTNAME);
        assertEquals(result,DEFAULT_EMPLOYEE);
    }
    @Test
    public void removeWhenEmployeeNotFoundTest() {
        EmployeeNotFoundException thrown = assertThrows(
                EmployeeNotFoundException.class,
                () -> out.remove(VALID_FIRSTNAME, VALID_LASTNAME));
        assertEquals(thrown.getMessage(),EMPLOYEE_NOT_FOUND_MESSAGE);
    }
    @Test
    public void findTest() {
        out.add(VALID_FIRSTNAME, VALID_LASTNAME, DEPARTMENT, SALARY);
        Employee result = out.find(VALID_FIRSTNAME, VALID_LASTNAME);
        assertEquals(result, DEFAULT_EMPLOYEE);
    }
    @Test
    public void findWhenEmployeeNotFoundTest() {
        EmployeeNotFoundException thrown = assertThrows(
                EmployeeNotFoundException.class,
                () -> out.find(VALID_FIRSTNAME, VALID_LASTNAME));
        assertEquals(thrown.getMessage(),EMPLOYEE_NOT_FOUND_MESSAGE);
    }
    @Test
    public void getAllTest() {
        out.add(VALID_FIRSTNAME, VALID_LASTNAME, DEPARTMENT, SALARY);
       Collection<Employee> result = out.findAll();
       Map<String,Employee> test = new HashMap<>();
       test.put(VALID_KEY,DEFAULT_EMPLOYEE);
       Collection<Employee> expected = test.values();
       assertIterableEquals(expected,result);
    }


}
