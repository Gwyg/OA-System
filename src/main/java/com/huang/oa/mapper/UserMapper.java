package com.huang.oa.mapper;

import com.github.pagehelper.Page;
import com.huang.oa.pojo.dto.DepartmentMemberDTO;
import com.huang.oa.pojo.dto.RegisterDTO;
import com.huang.oa.pojo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into users (username,password,department_id,role,status)  " +
            "values (#{username},#{password},#{departmentId},#{role})")
    Integer insert(RegisterDTO registerDTO);

    @Select("select * from users where username = #{username}")
    User getByUsername(String username);

    @Select("select * from users where department_id = #{departmentId}")
    List<User> getById(Integer departmentId);

    List<Integer> getDepartmenIdByUserIds(List<Integer> userIds);

    @Select("select * from users where user_id = #{userId}")
    User getByUserId(int userId);

    @Select("select role from users where user_id = #{id}")
    String getRoleById(Integer id);

    @Update("update users set department_id = #{departmentId} where user_id = #{userId}")
    void updateDepartmentIdById(Integer userId, Integer departmentId);

    @Select("select department_id from users where user_id = #{userId}")
    int getDepartmentIdById(int userId);
}
