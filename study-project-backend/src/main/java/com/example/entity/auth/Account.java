package com.example.entity.auth;

import lombok.Data;

/**
 * account表实体类
 * @author Jinze
 */
@Data
public class Account {
    int id;
    String email;
    String username;
    String password;
}
