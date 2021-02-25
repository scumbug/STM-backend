package com.wongc.stm.model;

import com.wongc.stm.model.enums.ContactType;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("contacts")
public class Contact {
    private @Id Long contactId;
    private ContactType contactType;
    private Long userId;
    private String contactNumber;
    private String contactEmail;
}
