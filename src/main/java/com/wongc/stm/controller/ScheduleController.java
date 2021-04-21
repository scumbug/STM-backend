package com.wongc.stm.controller;

import com.wongc.stm.model.Schedule;
import com.wongc.stm.service.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/schedules")
@PreAuthorize("hasRole('SUPER') or hasRole('ADMIN')")
public class ScheduleController {
    @Autowired
    ScheduleServiceImpl service;

    /*
     * Standard CRUD endpoints
     */

    @GetMapping("")
    public List<Schedule> getSchedules() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Schedule> findById(@PathVariable Long id) {
        Optional<Schedule> Schedule = service.findById(id);
        if(!Schedule.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Schedule not found");
        }
        return Schedule;
    }

    @PostMapping("")
    public Schedule saveSchedule(@RequestBody Schedule schedule) {
        return service.save(schedule);
    }

    @PutMapping("/{id}")
    public Schedule updateSchedule(@RequestBody Schedule schedule) {
        if(!service.existsById(schedule.getScheduleId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Schedule not found");
        }
        Schedule res = service.update(schedule);
        if(res == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Schedule not found");
        }
        return res;
    }

    @DeleteMapping("/{id}")
    public Map<String, Object> deleteScheduleById(@PathVariable Long id){
        if(!service.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Schedule not found");
        }
        service.deleteById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("status","Schedule deleted");
        return map;
    }

    @GetMapping("/{id}/confirm")
    public Schedule confirmSchedule(@PathVariable Long id) {
        return service.confirmSchedule(id);
    }


}
