package com.wongc.stm.model;

import java.sql.Date;

import com.wongc.stm.model.enums.ScheduleStatus;
import org.springframework.data.annotation.Id;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("schedules")
public class Schedule {
    private @Id Long scheduleId;
    private Long amenityId;
    private Long unitId;
    private Date scheduleDate;
    private ScheduleStatus scheduleStatus;
}
