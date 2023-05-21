package com.example.mockitotests.service;

import com.example.mockitotests.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee findMaxDepartmentSalary(Integer department);
    Employee findMinDepartmentSalary(Integer department);
    List<Employee> printEmployeesInThisDepartment(Integer department);
    Map<Integer, List<Employee>> printAll();

    double amountOfSalariesOfDepartment(Integer department);

}
