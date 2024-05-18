package com.huang.oa.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DepartmentsMapper {
    int getManagerIdByGroupId(Integer groupId);

    @Select("select manager_id from departments where department_id = #{departmentId}")
    int getManagerIdById(Integer departmentId);
}
