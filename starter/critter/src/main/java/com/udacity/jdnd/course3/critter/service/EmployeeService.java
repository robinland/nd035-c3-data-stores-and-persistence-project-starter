package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;

import java.util.List;

public interface EmployeeService {

    public Employee saveEmployee(Employee employee);

    public Employee findEmployeeById(Long id);

    public List<Employee> findEmployeeForService(EmployeeRequestDTO requestDTO);

    public List<Employee> findEmployeeByIds(List<Long> ids);
}
