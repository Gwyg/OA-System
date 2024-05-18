package com.huang.oa.pojo.entity;

import lombok.Data;

@Data
public class WorkGroup {
    private Integer groupId;
    private Integer departmentId;
    private String groupName;
    private Integer leaderId;
}
