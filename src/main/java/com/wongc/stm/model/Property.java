package com.wongc.stm.model;

import com.wongc.stm.model.enums.ManagementStatus;
import com.wongc.stm.model.enums.PropertyStatus;
import com.wongc.stm.model.enums.PropertyType;

import org.springframework.data.annotation.Id;

import lombok.Data;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.util.Set;

@Data
@Table("properties")
public class Property {
    private @Id Long propertyId;
    private String name;
    private PropertyType propertyType;
    private String address;
    private ManagementStatus managementStatus;
    private PropertyStatus propertyStatus;
    private double maintenanceFee;
    private Timestamp builtDate;

    @MappedCollection(keyColumn = "property_id", idColumn = "property_id")
    private Set<Unit> units;

    // TODO floor plan. use blob?

}