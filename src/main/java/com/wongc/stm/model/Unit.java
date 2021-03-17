package com.wongc.stm.model;

import org.springframework.data.annotation.Id;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("units")
public class Unit {
    private @Id Long unitId;
    private Long propertyId;
    private String unitNumber;
    private double floorArea;
    // TODO floorplan, use blob?

    //@MappedCollection(idColumn = "tenant_id")
    //private Tenant tenant;
}
