package com.example.mockitotests.service;

import com.example.mockitotests.Employee;
import com.example.mockitotests.exceptions.EmployeeAlreadyAddedException;
import com.example.mockitotests.exceptions.EmployeeNotFoundException;
import com.example.mockitotests.exceptions.InvalidInputException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees;
    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee add(String firstName, String lastName, int department, double salary) {
        validateInput(firstName,lastName);
        Employee employee = new Employee(firstName, lastName, department, salary);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }


    @Override
    public Employee remove(String firstName, String lastName) {
        validateInput(firstName,lastName);
        String key = getKey(firstName, lastName);
        if (employees.containsKey(key)) {
            return employees.remove(key);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee find(String firstName, String lastName) {
        validateInput(firstName,lastName);
        String key = getKey(firstName, lastName);
        if (employees.containsKey(key)) {
            return employees.get(key);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

    private String getKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    private void validateInput(String firstName, String lastName) {
        if (!(isAlpha(firstName) && isAlpha(lastName))) {
            throw new InvalidInputException();
        }
    }

    public void initTestMap() {
        add("Налог", "Сдоходов>", 1,12_000);
        add("Рекорд", "Надоев>", 2,13_000);
        add("Захват", "Покоев>", 3,14_000);
        add("Вагон", "Опохмелян>", 4,15_000);
        add("Бидон", "Отстоев>", 5,16_000);
        add("Ушат", "Помоев>", 1,17_000);
        add("Рулон", "Обоев>", 2,18_000);
        add("Отлов", "Приматов>", 3,19_000);
        add("Разгул", "Маньяков>", 4,20_000);
        add("Распил", "Самшитов>", 5,21_000);
    }
}
