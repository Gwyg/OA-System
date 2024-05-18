package com.huang.oa.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepartmentRequestDTO {
    private String requestType;
    private Integer departmentId;

    @JsonIgnore
    private Integer userId;
    @JsonIgnore
    private String status;
    @JsonIgnore
    private LocalDateTime requestTime;

}
