package com.huang.oa.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepartmentRequest {
    private Integer requestId;
    private Integer userId;
    private Integer departmentId;
    private String status;
    private LocalDateTime requestTime;
    private String requestType;
}
