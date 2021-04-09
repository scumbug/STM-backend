package com.wongc.stm.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wongc.stm.model.enums.UserType;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("users")
public class User {
    private @Id Long userId;
    private UserType type;
    private String username;
    private @JsonIgnore String password;
    private String name;

    @MappedCollection(keyColumn = "user_id", idColumn = "user_id")
    private Set<Contact> contacts;

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

}
