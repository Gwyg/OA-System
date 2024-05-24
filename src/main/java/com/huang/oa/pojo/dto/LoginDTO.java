package com.huang.oa.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class LoginDTO {

    private String username;
    private String password;

}
