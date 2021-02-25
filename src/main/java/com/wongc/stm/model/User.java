package com.wongc.stm.model;

import com.wongc.stm.model.enums.UserType;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("users")
public class User {
    private @Id Long userId;
    private UserType type;
    private String username;
    private String password;
    private String name;
    
    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

}
