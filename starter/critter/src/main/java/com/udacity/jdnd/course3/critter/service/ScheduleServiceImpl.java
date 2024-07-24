package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.data.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    ScheduleRepository repository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public Schedule saveSchedule(Schedule schedule) {
        return repository.save(schedule);
    }

    @Override
    public List<Schedule> getAllSchedule() {
        return repository.findAll();
    }

    @Override
    public List<Schedule> getScheduleByEmployeeId(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(new Employee());
        return employee.getSchedules();
    }

    @Override
    public List<Schedule> getScheduleByPetId(Long petId) {
        Pet pet = petRepository.findById(petId).orElse(new Pet());
        return pet.getSchedules();
    }

    @Override
    public List<Schedule> getScheduleByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(new Customer());
        return customer.getPets().stream().map(Pet::getSchedules).flatMap(Collection::stream).collect(Collectors.toList());
    }
}
