package com.wongc.stm.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("settings")
public class Setting {
    private @Id Long settingId;
    private String name;
    private String content;
}
