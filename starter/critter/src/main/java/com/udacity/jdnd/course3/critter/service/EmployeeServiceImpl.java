package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> findEmployeeForService(EmployeeRequestDTO requestDTO) {
        List<Employee> lsEmployee =  employeeRepository.getAllEmployeeAvailable(requestDTO.getDate().getDayOfWeek());
        return lsEmployee.stream().filter(e->e.getSkills().containsAll(requestDTO.getSkills())).collect(Collectors.toList());
    }

    @Override
    public List<Employee> findEmployeeByIds(List<Long> ids) {
        return employeeRepository.findAllById(ids);
        //employeeRepository.findAllEmployeeByIds(ids);

    }


}
