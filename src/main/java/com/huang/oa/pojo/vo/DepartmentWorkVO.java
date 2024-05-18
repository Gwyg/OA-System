package com.huang.oa.pojo.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DepartmentWorkVO {
    private Integer workId;
    private String title;
    private String description;
    private LocalDate deadline;
}
