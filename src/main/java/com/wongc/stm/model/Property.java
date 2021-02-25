package com.wongc.stm.model;

import com.wongc.stm.model.enums.ManagementStatus;
import com.wongc.stm.model.enums.PropertyStatus;
import com.wongc.stm.model.enums.PropertyType;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Property {
    private @Id Long propertyId;
    private String name;
    private PropertyType propertyType;
    private String address;
    private ManagementStatus managementStatus;
    private PropertyStatus propertyStatus;
    private double maintenanceFee;
    
    // TODO floor plan. use blob?

}