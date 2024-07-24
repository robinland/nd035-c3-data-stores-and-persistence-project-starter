package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.data.Schedule;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    EmployeeService employeeServiceImpl;

    @Autowired
    PetService petServiceImpl;

    @Autowired
    CustomerService customerServiceImpl;

    @Autowired
    ScheduleService scheduleServiceImpl;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleServiceImpl.saveSchedule(convertDTOToSchedule(scheduleDTO));
        for (Employee employee : schedule.getEmployees()){
            employee.getSchedules().add(schedule);
            employeeServiceImpl.saveEmployee(employee);
        }
        for (Pet pet : schedule.getPets()) {
            pet.getSchedules().add(schedule);
            petServiceImpl.save(pet);
        }

        return convertScheduleToDTO(schedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleServiceImpl.getAllSchedule().stream().map(this::convertScheduleToDTO).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> lsSchedule = scheduleServiceImpl.getScheduleByPetId(petId);
        return lsSchedule.stream().map(this::convertScheduleToDTO).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> lsSchedule = scheduleServiceImpl.getScheduleByEmployeeId(employeeId);
        return lsSchedule.stream().map(this::convertScheduleToDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> lsSchedule = scheduleServiceImpl.getScheduleByCustomerId(customerId);
        return lsSchedule.stream().map(this::convertScheduleToDTO).collect(Collectors.toList());
    }

    private ScheduleDTO convertScheduleToDTO(Schedule schedule){
        ScheduleDTO dto = new ScheduleDTO();
        BeanUtils.copyProperties(schedule,dto);
        dto.setEmployeeIds(schedule.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()));
        dto.setPetIds(schedule.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        return dto;
    }

    private Schedule convertDTOToSchedule(ScheduleDTO dto){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(dto,schedule);
        schedule.setEmployees(employeeServiceImpl.findEmployeeByIds(dto.getEmployeeIds()));
        schedule.setPets(petServiceImpl.findPetsByIds(dto.getPetIds()));
        return schedule;
    }
}
