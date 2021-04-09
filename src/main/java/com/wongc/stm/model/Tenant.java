package com.wongc.stm.model;

import com.wongc.stm.model.enums.TenantStatus;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("tenants")
public class Tenant {
    private @Id Long tenantId;
    private Long userId;
    private Long assignedAgent;
    private String company;
    private TenantStatus tenantStatus;
    private String notes;

    //@MappedCollection(keyColumn = "assigned_agent", idColumn = "user_id")
    //private User agent;

    //@MappedCollection(idColumn = "user_id")
    //private Lease leasedUnit;


}