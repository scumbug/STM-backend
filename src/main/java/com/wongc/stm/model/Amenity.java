package com.wongc.stm.model;

import java.sql.Date;

import com.wongc.stm.model.enums.Days;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Amenity {
    private @Id Long amenityId;
    private String name;
    private String details;
    private String contactNumber;
    private boolean serviceStatus;
    private boolean availability;
    private Date cutoff;
    private Days availableDays;
}
