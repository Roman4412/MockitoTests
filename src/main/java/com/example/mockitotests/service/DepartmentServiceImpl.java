package com.example.mockitotests.service;

import java.util.Comparator;

import com.example.mockitotests.Employee;
import com.example.mockitotests.exceptions.InvalidDepartmentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employService;
    @Autowired
    public DepartmentServiceImpl (EmployeeServiceImpl employServiceImpl) {
        this.employService = employServiceImpl;
    }

    @Override
    public Employee findMaxDepartmentSalary(Integer department) {
        validateDepartment(department);
        return employService.findAll().stream()
                .filter(emp -> emp.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary)).orElseThrow();

    }
    @Override
    public Employee findMinDepartmentSalary(Integer department) {
        validateDepartment(department);
        return employService.findAll().stream()
                .filter(emp -> emp.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary)).orElseThrow();

    }

    @Override
    public List<Employee>printEmployeesInThisDepartment(Integer department) {
        validateDepartment(department);
        return employService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }
    @Override
    public Map<Integer, List<Employee>> printAll() {
        return employService.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));


    }

    @Override
    public double amountOfSalariesOfDepartment(Integer department) {
        validateDepartment(department);
        return employService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department).mapToDouble(Employee::getSalary).sum();
    }
    private void validateDepartment(Integer department) {
        if ((department == null)||(department <= 0) ||(department > 5)) {
            throw new InvalidDepartmentException();
        }
    }
}
