package com.huang.oa.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RolePermissionsMapper {
    @Select("select permission from role_permissions where role = #{role}")
    List<String> getPermissions(String role);
}
