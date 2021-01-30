package com.project.entity;

import lombok.Data;

@Data
public class Account {
    private String id;
    private String username;
    private String password;
    private String perms;
    private String role;
}
