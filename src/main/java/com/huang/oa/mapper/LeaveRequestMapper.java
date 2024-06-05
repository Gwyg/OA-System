package com.huang.oa.mapper;

import com.huang.oa.pojo.dto.LeaveRequestDTO;
import com.huang.oa.pojo.entity.LeaveRequest;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LeaveRequestMapper {
    @Select("select * from leave_requests where user_id = #{userId}")
    LeaveRequest getByUserId(int userId);

    @Insert("insert into leave_requests (user_id, reason, status, start_date, end_date) " +
            "VALUES (#{userId},#{reason},#{status},#{startTime},#{endTime})")
    void insertAll(LeaveRequestDTO requestDTO);

    @Select("select * from leave_requests where request_id = #{requestId}")
    LeaveRequest getById(Integer requestId);

    @Update("update leave_requests set status = #{status} where request_id = #{requestId}")
    void updateStatusById(Integer requestId, String status);

    @Delete("delete from leave_requests where request_id = #{requestId}")
    void delete(Integer requestId);

    @Update("update leave_requests set reason = #{reason},status = #{status} where request_id = #{requestId}")
    void updateReasonById(Integer requestId, String reason, String status);

    @Select("select * from leave_requests")
    List<LeaveRequest> getAll();
}
