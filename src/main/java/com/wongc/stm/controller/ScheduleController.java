package com.wongc.stm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.wongc.stm.model.Schedule;
import com.wongc.stm.service.ScheduleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    @Autowired
    private ScheduleService service;

    /*
     * Standard CRUD endpoints
     */

    @GetMapping("/")
    public List<Schedule> getSchedules() {
        return (List<Schedule>) service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Schedule> findById(@PathVariable Long id) {
        Optional<Schedule> Schedule = service.findById(id);
        if(Schedule.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Schedule not found");
        }
        return Schedule;
    }

    @PostMapping("/{id}")
    public Schedule saveSchedule(@RequestBody Schedule Schedule) {
        Schedule res = service.save(Schedule);
        return res;
    }

    @PutMapping("/{id}")
    public Schedule updatSchedule(@RequestBody Schedule Schedule) {
        if(!service.existsById(Schedule.getScheduleId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Schedule not found");
        }
        Schedule res = service.update(Schedule);
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
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status","Schedule deleted");
        return map;
    }


}
