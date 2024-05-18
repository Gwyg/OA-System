package com.huang.oa.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class WorkGroupDTO {
    @JsonIgnore
    private Integer groupId;
    private Integer departmentId;
    private String groupName;
    private Integer leaderId;
    private List<Integer> userIds;
}
