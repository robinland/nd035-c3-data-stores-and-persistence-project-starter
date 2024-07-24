package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Pet;

import java.util.List;

public interface PetService {
    public Pet save(Pet pet);
    public List<Pet> getAllPet();
    public Pet findPetById(Long id);
    public List<Pet> findPetByCustomerId(Long id);

    public List<Pet> findPetsByIds(List<Long> ids);
}
