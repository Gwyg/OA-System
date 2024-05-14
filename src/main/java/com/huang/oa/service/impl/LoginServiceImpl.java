package com.huang.oa.service.impl;

import cn.dev33.satoken.stp.StpUtil;
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
            // TODO
            throw new UsernameExistsException("用户名已存在");
        }
        String password = DigestUtils.md5DigestAsHex(registerDTO.getPassword().getBytes());
        registerDTO.setPassword(password);
        return userMapper.insert(registerDTO);
    }

    @Override
    public void login(LoginDTO loginDTO) {
        User user = userMapper.getByUsername(loginDTO.getUsername());
        if(user == null) {
            // TODO 把信息单独提出来
            throw new UsernameErrorException("用户名不存在");
        }
        String password = DigestUtils.md5DigestAsHex(loginDTO.getPassword().getBytes());
        if (!password.equals(user.getPassword())) {
            // TODO
            throw new PasswordErrorException("密码错误");
        }
        StpUtil.login(user.getUserId());
    }
}
