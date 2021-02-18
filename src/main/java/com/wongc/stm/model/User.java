package com.wongc.stm.model;

import com.wongc.stm.model.enums.UserType;

public class User {
    private Long userId;
    private UserType type;
    private String username;
    private String password;
    private String name;
    private String primaryContact;
    private String primaryEmail;

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

}
