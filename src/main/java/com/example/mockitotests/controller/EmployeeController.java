package com.example.mockitotests.controller;

import com.example.mockitotests.Employee;
import com.example.mockitotests.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController()
public class EmployeeController {
    private final EmployeeServiceImpl employServiceImpl;
    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employServiceImpl = employeeServiceImpl;
    }
    @GetMapping("/add")
    public Employee add(@RequestParam(required = false)String firstName,
                        @RequestParam(required = false) String lastName,
                        @RequestParam(required = false) int department,
                        @RequestParam(required = false) double salary) {
        return employServiceImpl.add(firstName, lastName,department, salary);
    }
    @GetMapping("/remove")
    public Employee remove(@RequestParam(required = false) String firstName,
                           @RequestParam(required = false) String lastName) {
        return employServiceImpl.remove(firstName,lastName);
    }
    @GetMapping("/find")
    public Employee find(@RequestParam(required = false) String firstName,
                           @RequestParam(required = false) String lastName) {
        return employServiceImpl.find(firstName,lastName);
    }

    @GetMapping("/findAll")
    public Collection<Employee> findAll() {
        return employServiceImpl.findAll();
    }
    @GetMapping("/init")
    public void initTestMap() {
        employServiceImpl.initTestMap();
    }
}
