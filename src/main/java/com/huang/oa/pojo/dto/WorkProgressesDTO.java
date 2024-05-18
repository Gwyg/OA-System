package com.huang.oa.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WorkProgressesDTO {
    private Integer conteentId;
    private String progressStatus;
    private LocalDateTime updateTime;
    private Integer isEditable;
}
