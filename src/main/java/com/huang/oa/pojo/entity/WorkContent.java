package com.huang.oa.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WorkContent {
    private Integer contentId;
    private Integer groupId;
    private String title;
    private String description;
    private LocalDateTime deadline;
}
