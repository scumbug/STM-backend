package com.wongc.stm.wrapper;

import lombok.Data;

import java.util.List;

@Data
public class AuthWrapper {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private List<String> roles;

    public AuthWrapper(String accessToken, Long id, String username, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }

}
