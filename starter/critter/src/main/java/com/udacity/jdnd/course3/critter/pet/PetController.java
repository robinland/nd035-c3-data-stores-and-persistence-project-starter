package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    CustomerService customerServiceImpl;

    @Autowired
    PetService petServiceImpl;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = petServiceImpl.save(convertDTOToPet(petDTO));
        return convertPetToDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petServiceImpl.findPetById(petId);
        return convertPetToDTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> lsPet = petServiceImpl.getAllPet();
        return lsPet.stream().map(this::convertPetToDTO).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> lsPet = petServiceImpl.findPetByOwnerId(ownerId);
        return lsPet.stream().map(this::convertPetToDTO).collect(Collectors.toList());
    }

    private Pet convertDTOToPet(PetDTO dto){
        Pet pet = new Pet();
        BeanUtils.copyProperties(dto,pet);
        pet.setOwner(customerServiceImpl.getCustomerById(dto.getOwnerId()));
        return pet;
    }

    private PetDTO convertPetToDTO(Pet pet){
        PetDTO dto = new PetDTO();
        BeanUtils.copyProperties(pet,dto);
        dto.setOwnerId(pet.getOwner().getId());
        return dto;
    }

}
