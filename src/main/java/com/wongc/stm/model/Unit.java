package com.wongc.stm.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Unit {
    private @Id Long unitId;
    private Long propertyId;
    private String unitNumber;
    private double floorArea;
    // TODO floorplan, use blob?
    
}
