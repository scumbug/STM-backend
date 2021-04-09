package com.wongc.stm.repository;

import com.wongc.stm.model.Lease;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaseRepository extends PagingAndSortingRepository<Lease, Long>{

    @Query("SELECT * FROM leases WHERE (DAYOFMONTH(start_date) = :day AND MONTH(start_date) = :month)")
    List<Lease> findByDayMonth(@Param("day") int day, @Param("month") int month);

    Lease findByTenantId(Long tenantId);
}
