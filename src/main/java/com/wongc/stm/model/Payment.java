package com.wongc.stm.model;

import java.sql.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Payment {
    private @Id Long paymentId;
    private Long leaseId;
    private double amount;
    private boolean paymentStatus;
    private Date paymentDate;
    private Date paymentPeriod;
    // TODO: payment proof img as blob?
}
