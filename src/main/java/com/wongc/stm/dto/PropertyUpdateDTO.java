package com.wongc.stm.dto;

import com.wongc.stm.model.enums.ManagementStatus;
import com.wongc.stm.model.enums.PropertyStatus;
import com.wongc.stm.model.enums.PropertyType;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class PropertyUpdateDTO {
    private Long propertyId;
    private String name;
    private PropertyType propertyType;
    private String address;
    private ManagementStatus managementStatus;
    private PropertyStatus propertyStatus;
    private double maintenanceFee;
    private Timestamp builtDate;
}
