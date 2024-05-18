package com.huang.oa.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepartmentAnnouncement {
    private Integer announcementId;
    private Integer departmentId;
    private String title;
    private String content;
    private LocalDateTime publishDate;
}
