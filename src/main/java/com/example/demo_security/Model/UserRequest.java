package com.example.demo_security.Model;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
@Data
public class UserRequest {
    @NotNull
    private String username;

    @NotNull
    private String password;
    private String name;
    private String roles;

}
