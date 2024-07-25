package com.udacity.jdnd.course3.critter.data;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection
    @JoinTable(name = "employee_skill")
    private Set<EmployeeSkill> skills = new HashSet<>();

    @ElementCollection
    @JoinTable(name = "employee_dow_available")
    @Column(name = "daysAvailable")
    private Set<DayOfWeek> daysAvailable = new HashSet<>();

    @ManyToMany(mappedBy = "employees")
    List<Schedule> schedules = new ArrayList<>();

}
