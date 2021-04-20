package com.wongc.stm.service;

import java.util.List;
import java.util.Optional;

import com.wongc.stm.model.Schedule;
import com.wongc.stm.model.enums.ScheduleStatus;
import com.wongc.stm.repository.ScheduleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository repository;

    @Override
    public List<Schedule> findAll() {
        return (List<Schedule>) repository.findAll();
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Schedule update(Schedule Schedule) {
        Optional<Schedule> res = repository.findById(Schedule.getScheduleId());
        if(!res.isPresent())
            return null;
        Schedule tmp = res.get();
        return repository.save(tmp);
    }

    @Override
	public void deleteById(Long id) {
        repository.deleteById(id);
	}

    @Override
	public Schedule save(Schedule Schedule) {
		return repository.save(Schedule);
	}

    @Override
    public Schedule confirmSchedule(Long id) {
        Schedule schedule = repository.findById(id).get();
        schedule.setScheduleStatus(ScheduleStatus.CONFIRMED);
        return repository.save(schedule);
    }

}
