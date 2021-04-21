package com.wongc.stm.model;

import java.sql.Blob;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;

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
    private String paymentProof;

}
