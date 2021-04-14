package com.wongc.stm.repository;

import com.wongc.stm.model.Schedule;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends PagingAndSortingRepository<Schedule, Long>{

}
