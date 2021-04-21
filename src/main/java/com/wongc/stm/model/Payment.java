package com.wongc.stm.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

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
    private String paymentProof;

}
