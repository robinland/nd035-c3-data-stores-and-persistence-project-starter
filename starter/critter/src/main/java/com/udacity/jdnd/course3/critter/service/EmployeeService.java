package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Employee;

import java.util.Optional;

public interface EmployeeService {

    public Employee saveEmployee(Employee employee);

    public Optional<Employee> findEmployeeById(Long id);


}
