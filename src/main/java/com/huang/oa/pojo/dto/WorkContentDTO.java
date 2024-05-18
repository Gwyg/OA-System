package com.huang.oa.pojo.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class WorkContentDTO {
    private Integer contentId;
    private Integer groupId;
    private String title;
    private String description;
    @DateTimeFormat(pattern = "YYYY-MM-DD HH:MM:SS")
    private LocalDateTime deadline;
}
