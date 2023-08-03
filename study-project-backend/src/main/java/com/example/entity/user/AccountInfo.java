package com.example.entity.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class AccountInfo {
    private int uid;
    @Length(min = 2, max = 8)
    private String username;
    private String sex;
    @Length(min = 8,max = 11)
    private String qq;
    @Length(max = 30)
    private String wx;
    @Length( max = 11)
    private String phone;
    @Length(max = 50)
    private String blog;
    @Length(max = 500)
    private String user_desc;
}
