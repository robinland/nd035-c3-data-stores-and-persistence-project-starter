package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {


   /* @Query(
            "select s from Schedule s" +
                    " inner join s.pets p " +
                    " where p.id = :petId"
    )
    List<Schedule> getAllScheduleByPetId(@Param("petId") Long petId);

    @Query(
            "select s from Schedule s " +
                    " inner join s.employee e " +
                    " where e.id = :employeeId"
    )
    List<Schedule> getAllScheduleByEmployeeId(@Param("employeeId") Long employeeId);

    @Query(
            "select s from Schedule s " +
                    " inner join s.pets p" +
                    " inner join p.customer c " +
                    " where c.id = :customerId"
    )
    List<Schedule> getAllScheduleByCustomerId(@Param("customerId") Long customerId);*/
}
