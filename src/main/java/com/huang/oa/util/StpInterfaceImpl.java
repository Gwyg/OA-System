package com.huang.oa.util;

import cn.dev33.satoken.stp.StpInterface;
import com.huang.oa.mapper.RolePermissionsMapper;
import com.huang.oa.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RolePermissionsMapper rolePermissionsMapper;

    @Override
    public List<String> getPermissionList(Object o, String s) {
        Integer id = Integer.valueOf(o.toString());
        String role = userMapper.getRoleById(id);
        List<String> permissionList = rolePermissionsMapper.getPermissions(role);
        return permissionList;
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        Integer id = Integer.valueOf(o.toString());
        String role = userMapper.getRoleById(id);
        return List.of(role);
    }
}
