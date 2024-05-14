package com.huang.oa.service;

import com.huang.oa.pojo.dto.LoginDTO;
import com.huang.oa.pojo.dto.RegisterDTO;
import com.huang.oa.pojo.entity.User;

public interface LoginService {
    Integer register(RegisterDTO registerDTO);

    void login(LoginDTO loginDTO);
}
