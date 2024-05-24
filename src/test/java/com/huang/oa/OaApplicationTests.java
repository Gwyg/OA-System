package com.huang.oa;

import cn.dev33.satoken.stp.StpUtil;
import com.huang.oa.common.Role;
import com.huang.oa.mapper.*;
import com.huang.oa.pojo.dto.RegisterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class OaApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DepartmentsMapper departmentsMapper;
    @Autowired
    private WorkContentMapper workContentMapper;
    @Autowired
    private WorkMemberMapper workMemberMapper;

    public static void main(String[] args) {

    }


}
