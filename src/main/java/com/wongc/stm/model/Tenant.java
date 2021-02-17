package com.wongc.stm.model;

import com.wongc.stm.model.enums.TenantStatus;
import com.wongc.stm.model.enums.UserType;

public class Tenant extends User {
    private String company;
    private String primaryContact;
    private String primaryEmail;
    private String billingContact;
    private String billingEmail;
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
