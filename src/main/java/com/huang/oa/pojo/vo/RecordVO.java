package com.huang.oa.pojo.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class RecordVO {
    private Integer recordId;
    private String type;
    private Integer userId;
    private String status;
    @DateTimeFormat(pattern = "YYYY-MM-DD HH:MM:SS")
    private LocalDateTime time;
}
