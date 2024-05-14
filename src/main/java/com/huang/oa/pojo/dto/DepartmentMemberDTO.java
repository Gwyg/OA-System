package com.huang.oa.pojo.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

@Data
public class DepartmentMemberDTO implements Serializable{

    private Integer departmentId;
    private Integer page;
    private Integer pageSize;
}
