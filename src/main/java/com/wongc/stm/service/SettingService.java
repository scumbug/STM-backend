package com.wongc.stm.service;

import com.wongc.stm.model.Setting;

public interface SettingService {

    public Setting update(Setting setting);

    public Setting findByName(String name);
}
