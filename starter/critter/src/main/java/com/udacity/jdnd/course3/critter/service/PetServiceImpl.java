package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService{

    @Autowired
    PetRepository repository;

    @Override
    public Pet save(Pet pet) {
        return repository.save(pet);
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
    public List<Pet> findPetByOwnerId(Long id) {
        return repository.findByOwnerId(id);
    }
}
