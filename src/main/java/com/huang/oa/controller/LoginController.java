package com.huang.oa.controller;


import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;

import com.huang.oa.pojo.dto.LoginDTO;
import com.huang.oa.pojo.dto.RegisterDTO;
import com.huang.oa.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
@Tag(name = "登陆注册接口")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登陆接口
     * @param loginDTO 前端传入的账号信息
     * @return
     */
    @Operation(summary = "登陆接口")
    @PostMapping("/login")
    public SaResult login(@RequestBody LoginDTO loginDTO){
        log.info("用户登陆{}",loginDTO);
        loginService.login(loginDTO);
        return SaResult.data(StpUtil.getTokenInfo());
    }

    /**
     * 注册接口
     * @param registerDTO 前端传入的注册信息
     * @return
     */
    @PostMapping("/register")
    @Operation(summary = "注册接口")
    public SaResult register(@RequestBody RegisterDTO registerDTO){
        log.info("用户注册{}",registerDTO);
        int i = loginService.register(registerDTO);
        return i == 1?SaResult.ok():SaResult.error("用户注册失败");
    }

}
