package com.huang.oa.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huang.oa.common.Role;
import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String password;
    private Integer departmentId;
    private String role;

}
