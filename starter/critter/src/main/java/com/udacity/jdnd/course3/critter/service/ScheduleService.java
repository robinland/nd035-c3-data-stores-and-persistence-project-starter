package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Schedule;

import java.util.List;

public interface ScheduleService {

    public Schedule saveSchedule(Schedule schedule);

    public List<Schedule> getAllSchedule();

    public List<Schedule> getScheduleByEmployeeId(Long employeeId);

    public List<Schedule> getScheduleByPetId(Long petId);

    public List<Schedule> getScheduleByCustomerId(Long customerId);
}
