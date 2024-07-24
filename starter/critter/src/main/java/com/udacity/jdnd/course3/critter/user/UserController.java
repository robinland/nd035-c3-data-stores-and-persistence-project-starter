package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    EmployeeService employeeServiceImpl;

    @Autowired
    CustomerService customerServiceImpl;

    @Autowired
    PetService petServiceImpl;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
       Customer customer =  customerServiceImpl.saveCustomer(convertDTOToCustomer(customerDTO));
       return convertCustomerToCustomerDTO(customer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> lsCustomer = customerServiceImpl.getAllCustomer();
        return lsCustomer.stream().map(this::convertCustomerToCustomerDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Pet pet = petServiceImpl.findPetById(petId);
        Customer customer = pet.getCustomer();
        return convertCustomerToCustomerDTO(customer);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = convertDTOToEmployee(employeeDTO);
        employee = employeeServiceImpl.saveEmployee(employee);
        return convertEmployeeToDTO(employee);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return convertEmployeeToDTO(employeeServiceImpl.findEmployeeById(employeeId));
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        Employee employee = employeeServiceImpl.findEmployeeById(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeServiceImpl.saveEmployee(employee);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> lsEmployee = employeeServiceImpl.findEmployeeForService(employeeDTO);
        return lsEmployee.stream().map(this::convertEmployeeToDTO).collect(Collectors.toList());
    }

    public EmployeeDTO convertEmployeeToDTO(Employee employee){
        EmployeeDTO dto = new EmployeeDTO();
        BeanUtils.copyProperties(employee,dto);
        dto.setName(employee.getName());
        dto.setSkills(employee.getSkills());
        dto.setDaysAvailable(employee.getDaysAvailable());
        return dto;
    }

    public Employee convertDTOToEmployee(EmployeeDTO dto){
        Employee employee = new Employee();
        BeanUtils.copyProperties(dto,employee);
        employee.setSkills(dto.getSkills());
        employee.setDaysAvailable(dto.getDaysAvailable());
        return employee;
    }

    public CustomerDTO convertCustomerToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);
        if(customer.getPets() != null && customer.getPets().size() >0)
            customerDTO.setPetIds(customer.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        return  customerDTO;
    }

    public Customer convertDTOToCustomer(CustomerDTO dto){
        Customer customer = new Customer();
        BeanUtils.copyProperties(dto,customer);
        return customer;
    }

}
