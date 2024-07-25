package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetServiceImpl implements PetService{

    @Autowired
    PetRepository repository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Pet save(Pet pet) {
        pet =  repository.save(pet);
        Customer customer = pet.getCustomer();
        customer.getPets().add(pet);
        customerRepository.save(customer);
        return pet;
    }

    @Override
    public List<Pet> getAllPet() {
        return repository.findAll();
    }

    @Override
    public Pet findPetById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Pet> findPetByCustomerId(Long id) {
        return repository.findByCustomerId(id);
    }

    @Override
    public List<Pet> findPetsByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }
}
