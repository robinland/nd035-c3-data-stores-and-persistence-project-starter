package com.udacity.jdnd.course3.critter.data;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    @JoinTable(name = "schedule_employee",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Employee> employees = new LinkedList<>();

    @ManyToMany
    @JoinTable(name = "schedule_pet",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "pet_id"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Pet> pets = new LinkedList<>();

    @ElementCollection
    private Set<EmployeeSkill> activities;

    private LocalDate date;
}
