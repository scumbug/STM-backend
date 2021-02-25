package com.wongc.stm.model;

import java.sql.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Schedule {
    private @Id Long scheduleId;
    private Long amenityId;
    private Long unitId;
    private Date scheduleDate;
}
