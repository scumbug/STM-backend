package com.wongc.stm.dto;

import lombok.Data;

@Data
public class TenantDTO {

    private Long tenantId;
    private Long userId;
    private String name;
}
