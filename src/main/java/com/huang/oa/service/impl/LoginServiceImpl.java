package com.huang.oa.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.huang.oa.common.ErrorMessage;
import com.huang.oa.exception.PasswordErrorException;
import com.huang.oa.exception.UsernameErrorException;
import com.huang.oa.exception.UsernameExistsException;
import com.huang.oa.mapper.UserMapper;
import com.huang.oa.pojo.dto.LoginDTO;
import com.huang.oa.pojo.dto.RegisterDTO;
import com.huang.oa.pojo.entity.User;
import com.huang.oa.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer register(RegisterDTO registerDTO) {
        User user = userMapper.getByUsername(registerDTO.getUsername());
        if (user != null) {
            throw new UsernameExistsException(ErrorMessage.USER_NAME_ALREADY_EXISTS);
        }
        String password = DigestUtils.md5DigestAsHex(registerDTO.getPassword().getBytes());
        registerDTO.setPassword(password);
        return userMapper.insert(registerDTO);
    }

    @Override
    public void login(LoginDTO loginDTO) {
        User user = userMapper.getByUsername(loginDTO.getUsername());
        if(user == null) {

            throw new UsernameErrorException(ErrorMessage.USER_NOT_REGISTERED);
        }
        String password = DigestUtils.md5DigestAsHex(loginDTO.getPassword().getBytes());
        if (!password.equals(user.getPassword())) {
            throw new PasswordErrorException(ErrorMessage.WRONG_PASSWORD);
        }
        StpUtil.login(user.getUserId());
    }
}
