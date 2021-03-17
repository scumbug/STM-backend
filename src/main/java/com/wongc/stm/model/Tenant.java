package com.wongc.stm.model;

import com.wongc.stm.model.enums.TenantStatus;
import com.wongc.stm.model.enums.UserType;

import lombok.Data;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("tenants")
public class Tenant extends User {
    private Long tenantId;
    private String company;
    private TenantStatus tenantStatus;
    private String notes;
    private User assignedAgent;

    // force user type to be tenant
    @Override
    public void setType(UserType type) {
        super.setType(UserType.TENANT);
    }

    //@MappedCollection(idColumn = "unit_id")
    private Lease leasedUnit;

}
