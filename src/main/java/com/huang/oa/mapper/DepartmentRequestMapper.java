package com.huang.oa.mapper;

import com.huang.oa.pojo.dto.DepartmentRequestDTO;
import com.huang.oa.pojo.entity.DepartmentRequest;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DepartmentRequestMapper {


    @Select("select * from department_request where user_id = #{userId}")
    DepartmentRequest getByUserId(int userId);

    @Insert("insert into department_request (user_id, department_id, status, request_time, request_type) " +
            "VALUES (#{userId},#{departmentId},#{status},#{requestTime},#{requestType})")
    void insertAll(DepartmentRequestDTO requestDTO);

    @Select("select * from department_request where request_id = #{requestId}")
    DepartmentRequest getById(Integer requestId);

    @Delete("delete from department_request where request_id = #{requestId}")
    void delete(Integer requestId);

    @Update("update department_request set status = #{status} where request_id = #{requestId}")
    void updateStatusById(Integer requestId, String status);
}
