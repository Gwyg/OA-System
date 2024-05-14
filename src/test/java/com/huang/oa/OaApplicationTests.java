package com.huang.oa;

import com.huang.oa.common.Role;
import com.huang.oa.mapper.UserMapper;
import com.huang.oa.pojo.dto.RegisterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OaApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUsername("hyh");
        registerDTO.setPassword("123456");
        registerDTO.setDepartmentId(1);
        System.out.println(userMapper.insert(registerDTO));
    }
    @Test
    public void getByUsernameTest(){
        System.out.println(userMapper.getByUsername("hyh"));
    }

    @Test
    public void test(){
        System.out.println(userMapper.getById(0));
    }

}
