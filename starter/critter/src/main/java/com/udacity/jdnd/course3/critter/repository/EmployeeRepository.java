package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query(
            "select e from Employee e where :dayOfWeek member of e.daysAvailable"
    )
    List<Employee> getAllEmployeeAvailable(@Param("dayOfWeek") DayOfWeek dayOfWeek);

    @Query(
            "select e from Employee e where id in :ids"
    )
    List<Employee> findAllEmployeeByIds(@Param("ids") List<Long> ids);
}
