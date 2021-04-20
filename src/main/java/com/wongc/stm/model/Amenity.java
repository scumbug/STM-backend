package com.wongc.stm.model;

import java.sql.Date;
import java.util.EnumSet;
import java.util.Set;

import com.wongc.stm.model.enums.Days;

import org.springframework.data.annotation.Id;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("amenities")
public class Amenity {
    private @Id Long amenityId;
    private String name;
    private String details;
    private String contactNumber;
    private boolean amenityStatus;
    private boolean availability;
    private Date cutoff;
    private Set<String> availableDays;
}
