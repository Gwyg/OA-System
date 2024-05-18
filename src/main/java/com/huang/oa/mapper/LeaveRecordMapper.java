package com.huang.oa.mapper;


import com.huang.oa.pojo.entity.LeaveRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface LeaveRecordMapper {
    @Insert("insert into leave_record (request_id, user_id, reason, status, start_date, end_date) " +
            "values (#{requestId},#{userId},#{reason},#{status},#{LocalDateTime},#{endTime})")
    void insertAll(LeaveRequest request);

    @Update("update leave_requests set reason = #{reason},status = #{status} where request_id = #{requestId}")
    void updateReasonById(Integer requestId, String reason, String status);
}
