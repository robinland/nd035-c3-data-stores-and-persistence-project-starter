package com.udacity.jdnd.course3.critter.data;

import com.udacity.jdnd.course3.critter.pet.PetType;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private PetType type;

    @Column
    private String name;

    @Column
    private Date birthdate;

    @Column
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;


    @ManyToMany(mappedBy = "pets")
    private List<Schedule> schedules = new ArrayList<>();
}
