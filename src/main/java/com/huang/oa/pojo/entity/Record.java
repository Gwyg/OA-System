package com.huang.oa.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.xiaoymin.knife4j.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {

    private String type;
    private Integer userId;
    private String status;
    @DateTimeFormat(pattern = "YYYY-MM-DD HH:MM:SS")
    private LocalDateTime time;
}
