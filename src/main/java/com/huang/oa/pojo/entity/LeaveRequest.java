package com.huang.oa.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LeaveRequest {
    private Integer requestId;
    private Integer userId;
    private String reason;
    private String status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
