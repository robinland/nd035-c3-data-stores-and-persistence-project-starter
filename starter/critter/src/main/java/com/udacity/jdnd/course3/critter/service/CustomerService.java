package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Customer;

import java.util.List;

public interface CustomerService {

    public Customer saveCustomer(Customer owner);
    public List<Customer> getAllCustomer();
    public Customer getCustomerById(Long id);
}
