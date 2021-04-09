package com.wongc.stm.wrapper;

import com.wongc.stm.model.Contact;
import com.wongc.stm.model.Tenant;
import com.wongc.stm.model.User;
import lombok.Data;

import java.util.List;

@Data
public class TenantWrapper {
    private User user;
    private List<Contact> contact;
    private Tenant tenant;
    private User assignedAgent;

}
