package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Schedule;
import com.wongc.stm.repository.ScheduleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository repository;

    public List<Schedule> findAll() {
        return (List<Schedule>) repository.findAll();
    }

    public Optional<Schedule> findById(Long id) {
        return repository.findById(id);
    }

    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    public Schedule update(Schedule Schedule) {
        Optional<Schedule> res = repository.findById(Schedule.getScheduleId());
        if(res.isEmpty())
            return null;
        Schedule tmp = res.get();
        return repository.save(tmp);
    }

	public void deleteById(Long id) {
        repository.deleteById(id);
	}

	public Schedule save(Schedule Schedule) {
		return repository.save(Schedule);
	}

}
