package com.huang.oa.ws.pojo;

import lombok.Data;

@Data
public class ResultMassage {
    private boolean system;
    private String fromName;
    private Object message;
}
