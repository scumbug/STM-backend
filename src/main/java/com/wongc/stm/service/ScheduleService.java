package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Schedule;

public interface ScheduleService {
    public List<Schedule> findAll();
    public Optional<Schedule> findById(Long id);
    public boolean existsById(Long id);
    public Schedule update(Schedule Schedule);
    public void deleteById(Long id);
    public Schedule save(Schedule Schedule);

    public Schedule confirmSchedule(Long id);
}
