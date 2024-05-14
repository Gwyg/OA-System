package com.huang.oa.mapper;

import com.github.pagehelper.Page;
import com.huang.oa.pojo.dto.DepartmentMemberDTO;
import com.huang.oa.pojo.dto.RegisterDTO;
import com.huang.oa.pojo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into users (username,password,department_id,role) values (#{username},#{password},#{departmentId},#{role})")
    Integer insert(RegisterDTO registerDTO);

    @Select("select * from users where username = #{username}")
    User getByUsername(String username);

    @Select("select * from users where department_id = #{departmentId}")
    List<User> getById(Integer departmentId);
}
