package com.huang.oa.pojo.vo;

import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class AnnouncementVO {

    private Integer announcementId;
    private String title;
    private String content;
    private LocalDateTime publishDate;

}
