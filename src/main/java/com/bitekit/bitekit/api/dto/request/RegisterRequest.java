package com.bitekit.bitekit.api.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String tel;
    private String role; // ADMIN or STAFF
}
