package com.huang.oa.pojo.entity;

import lombok.Data;

@Data
public class User {
    private Integer UserId;
    private String username;
    private String password;
    private String salt;
    private String role;
    private Integer departmentId;
    private String status;
}
