package com.wongc.stm.repository;

import com.wongc.stm.model.Setting;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SettingRepository extends PagingAndSortingRepository<Setting, Long> {
    Setting findByName(String name);
}
