package com.wongc.stm.service;

import com.wongc.stm.model.Setting;

public interface SettingService {

    Setting update(Setting setting);

    Setting findByName(String name);
}
