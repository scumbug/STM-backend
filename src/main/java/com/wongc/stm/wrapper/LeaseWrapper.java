package com.wongc.stm.wrapper;

import com.wongc.stm.model.Contact;
import com.wongc.stm.model.Lease;
import lombok.Data;

import java.util.List;

@Data
public class LeaseWrapper {
    private Lease lease;
    private List<Contact> contacts;
}
