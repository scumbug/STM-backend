package com.wongc.stm.model;

import org.springframework.data.annotation.Id;

public class Unit {
    @Id
    private int unitId;
    private String unitNumber;
    private double floorArea;
    // TODO floorplan, use blob?
    
    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public double getFloorArea() {
        return floorArea;
    }

    public void setFloorArea(double floorArea) {
        this.floorArea = floorArea;
    }

    
}
