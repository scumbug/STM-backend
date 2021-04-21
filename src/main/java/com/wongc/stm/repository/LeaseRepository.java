package com.wongc.stm.repository;

import com.wongc.stm.dto.UnitRent;
import com.wongc.stm.model.Lease;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeaseRepository extends PagingAndSortingRepository<Lease, Long>{

    @Query("SELECT * FROM leases WHERE (DAYOFMONTH(start_date) = :day AND MONTH(start_date) = :month)")
    List<Lease> findByDayMonth(@Param("day") int day, @Param("month") int month);

    Lease findByTenantId(Long tenantId);

    @Query("SElECT * FROM leases WHERE unit_id = :unitId AND archive = 0")
    Optional<Lease> findByUnitId(@Param("unitId") Long id);

    @Query("SELECT rent,start_date FROM leases WHERE unit_id = :unitId")
    List<UnitRent> findHistoricRentByUnitId(@Param("unitId") Long id);
}
