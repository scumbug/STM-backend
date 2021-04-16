package com.wongc.stm.service;

import com.wongc.stm.model.Setting;
import com.wongc.stm.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingServiceImpl implements SettingService {
    @Autowired
    SettingRepository settingRepository;

    @Override
    public Setting update(Setting setting) {
        System.out.println(setting);
        return settingRepository.save(setting);
    }

    @Override
    public Setting findByName(String name) {
        return settingRepository.findByName(name);
    }
}
