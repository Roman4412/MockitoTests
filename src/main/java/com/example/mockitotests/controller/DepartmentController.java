package com.example.mockitotests.controller;

import com.example.mockitotests.Employee;
import com.example.mockitotests.service.DepartmentService;
import com.example.mockitotests.service.DepartmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController()
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentServiceImpl;
    @Autowired
    public DepartmentController(DepartmentServiceImpl departmentServiceImpl){
        this.departmentServiceImpl = departmentServiceImpl;
    }

    @GetMapping("/{dep}/salary/max")
    public Employee findMaxSalary(@PathVariable(required = false) Integer dep) {
        return departmentServiceImpl.findMaxDepartmentSalary(dep);
    }
    @GetMapping("/{dep}/salary/min")
    public Employee findMinSalary(@PathVariable(required = false) Integer dep) {
        return departmentServiceImpl.findMinDepartmentSalary(dep);
    }
    @GetMapping("/{dep}/dep-employees")
    public List<Employee> printAllDepartment(@PathVariable(required = false) Integer dep) {
        return departmentServiceImpl.printEmployeesInThisDepartment(dep);
    }
    @GetMapping("/all")
    public Map<Integer, List<Employee>> printAll() {
        return departmentServiceImpl.printAll();
    }

    @GetMapping("/{dep}/salary/sum")
    public double amountOfSalariesOfDepartment(@PathVariable(required = false) Integer dep) {
        return departmentServiceImpl.amountOfSalariesOfDepartment(dep);
    }
}
