package com.wongc.stm.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class UnitRent {
    private Long rent;
    private Date startDate;
}
