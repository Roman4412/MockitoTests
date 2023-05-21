package com.example.mockitotests;

import com.example.mockitotests.exceptions.InvalidDepartmentException;
import com.example.mockitotests.service.DepartmentServiceImpl;
import com.example.mockitotests.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeServiceImpl employeeServiceImpl;

    @InjectMocks
    private DepartmentServiceImpl departmentServiceImpl;

    public void getMock() {
        Mockito.when(employeeServiceImpl.findAll()).thenReturn(List.of(
                new Employee("Налог", "Сдоходов>", 1,12_000),
                new Employee("Рекорд", "Надоев>", 2,13_000),
                new Employee("Захват", "Покоев>", 3,14_000),
                new Employee("Вагон", "Опохмелян>", 4,15_000),
                new Employee("Бидон", "Отстоев>", 5,16_000),
                new Employee("Ушат", "Помоев>", 1,17_000),
                new Employee("Рулон", "Обоев>", 2,18_000),
                new Employee("Отлов", "Приматов>", 3,19_000),
                new Employee("Разгул", "Маньяков>", 4,20_000),
                new Employee("Распил", "Самшитов>", 5,21_000)));
    }

    @Test
    public void maxDepartmentSalaryTest() {
        getMock();
        double result = departmentServiceImpl.findMaxDepartmentSalary(1).getSalary();
        assertEquals(17_000,result);
    }


    @Test
    public void maxDepartmentSalaryInvalidDepartmentTest() {
        InvalidDepartmentException thrown = assertThrows(
                InvalidDepartmentException.class,
                () -> departmentServiceImpl.findMaxDepartmentSalary(0));
        assertEquals(thrown.getMessage(),"Invalid department");
    }

    @Test
    public void minSalaryFromDepartmentTest() {
        getMock();
        double result = departmentServiceImpl.findMinDepartmentSalary(1).getSalary();
        assertEquals(12_000,result);
    }

    @Test
    public void minDepartmentSalaryInvalidDepartmentTest() {
        InvalidDepartmentException thrown = assertThrows(
                InvalidDepartmentException.class,
                () -> departmentServiceImpl.findMinDepartmentSalary(0));
        assertEquals(thrown.getMessage(),"Invalid department");
    }

    @Test
    public void amountOfSalariesOfDepartmentTest() {
        getMock();
        double result = departmentServiceImpl.amountOfSalariesOfDepartment(1);
        assertEquals(29_000,result);
    }

    @Test
    public void amountOfSalariesOfDepartmentInvalidDepartmentTest() {
        InvalidDepartmentException thrown = assertThrows(
                InvalidDepartmentException.class,
                () -> departmentServiceImpl.amountOfSalariesOfDepartment(0));
        assertEquals(thrown.getMessage(),"Invalid department");
    }
    @Test
    public void printEmployeesInThisDepartmentTest() {
        getMock();
        List<Employee> result = departmentServiceImpl.printEmployeesInThisDepartment(1);
        List<Employee> expected = List.of(
                new Employee("Налог", "Сдоходов>", 1,12_000),
                new Employee("Ушат", "Помоев>", 1,17_000)
                );
        assertEquals(result,expected);
    }

    @Test
    public void employeesGroupedByDepartmentTest() {
        getMock();
        Map<Integer, List<Employee>> expected = Map.of(
                1,
                List.of(
                        new Employee("Налог", "Сдоходов>", 1,12_000),
                        new Employee("Ушат", "Помоев>", 1,17_000)
                ),
                2,
                List.of(
                        new Employee("Рекорд", "Надоев>", 2,13_000),
                        new Employee("Рулон", "Обоев>", 2,18_000)
                ),
                3,
                List.of(
                        new Employee("Захват", "Покоев>", 3,14_000),
                        new Employee("Отлов", "Приматов>", 3,19_000)),
                4,
                List.of(
                        new Employee("Вагон", "Опохмелян>", 4,15_000),
                        new Employee("Разгул", "Маньяков>", 4,20_000)
                        ),
                5,List.of(
                        new Employee("Бидон", "Отстоев>", 5,16_000),
                        new Employee("Распил", "Самшитов>", 5,21_000)
                )
        );
        Map<Integer,List<Employee>> result = departmentServiceImpl.printAll();
        assertEquals(result,expected);
    }

}