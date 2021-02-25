package com.wongc.stm.model;

import com.wongc.stm.model.enums.TenantStatus;
import com.wongc.stm.model.enums.UserType;

import lombok.Data;

@Data
public class Tenant extends User {
    private Long tenantId;
    private Long userId;
    private String company;
    private TenantStatus tenantStatus;
    private String notes;
    private Lease leasedUnit;
    private User assignedAgent;

    // force user type to be tenant
    @Override
    public void setType(UserType type) {
        super.setType(UserType.TENANT);
    }
}
