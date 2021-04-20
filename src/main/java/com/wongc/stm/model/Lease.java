package com.wongc.stm.model;

import java.sql.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("leases")
public class Lease {
    private @Id Long leaseId;
    private Long tenantId;
    private Long unitId;
    private Date startDate;
    private Date endDate;
    // TODO: lease agreement file as blob?
    private double rent;
    private boolean archive;
}
