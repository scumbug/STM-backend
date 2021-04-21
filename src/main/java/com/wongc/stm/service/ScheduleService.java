package com.wongc.stm.service;

import com.wongc.stm.model.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    List<Schedule> findAll();
    Optional<Schedule> findById(Long id);
    boolean existsById(Long id);
    Schedule update(Schedule Schedule);
    void deleteById(Long id);
    Schedule save(Schedule Schedule);

    Schedule confirmSchedule(Long id);
}
