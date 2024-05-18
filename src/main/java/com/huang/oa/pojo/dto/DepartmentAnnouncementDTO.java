package com.huang.oa.pojo.dto;

import cn.dev33.satoken.annotation.SaIgnore;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.xiaoymin.knife4j.annotations.Ignore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class DepartmentAnnouncementDTO {
    private Integer departmentId;
    private String title;
    private String content;
    @JsonIgnore
    private LocalDateTime publishDate;
}
