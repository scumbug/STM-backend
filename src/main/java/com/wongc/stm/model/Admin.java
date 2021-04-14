package com.wongc.stm.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("admin")
public class Admin {
    private @Id Long adminId;
    private String name;
    private String content;
}
