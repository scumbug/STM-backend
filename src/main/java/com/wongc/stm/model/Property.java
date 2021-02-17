package com.wongc.stm.model;

import com.wongc.stm.model.enums.ManagementStatus;
import com.wongc.stm.model.enums.PropertyStatus;
import com.wongc.stm.model.enums.PropertyType;

public class Property {
    private int propertyId;
    private String name;
    private PropertyType propertyType;
    private String address;
    private ManagementStatus managementStatus;
    private PropertyStatus propertyStatus;
    private double maintenanceFee;
    private Unit[] units;
    
    // TODO floor plan. use blob?

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ManagementStatus getManagementStatus() {
        return managementStatus;
    }

    public void setManagementStatus(ManagementStatus managementStatus) {
        this.managementStatus = managementStatus;
    }

    public PropertyStatus getPropertyStatus() {
        return propertyStatus;
    }

    public void setPropertyStatus(PropertyStatus propertyStatus) {
        this.propertyStatus = propertyStatus;
    }

    public double getMaintenanceFee() {
        return maintenanceFee;
    }

    public void setMaintenanceFee(double maintenanceFee) {
        this.maintenanceFee = maintenanceFee;
    }

    public Unit[] getUnits() {
        return units;
    }

    public void setUnits(Unit[] units) {
        this.units = units;
    }

    

}