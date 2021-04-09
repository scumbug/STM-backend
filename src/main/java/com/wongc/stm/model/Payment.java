package com.wongc.stm.model;

import java.sql.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("payments")
public class Payment {
    private @Id Long paymentId;
    private Long leaseId;
    private Long tenantId;
    private double amount;
    private int paymentStatus;
    private Date paymentDate;
    private Date paymentStartPeriod;
    private Date paymentEndPeriod;
    // TODO: payment proof img as blob?
}
