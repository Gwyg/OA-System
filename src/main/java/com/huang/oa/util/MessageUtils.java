package com.huang.oa.util;


import com.alibaba.fastjson.JSON;
import com.huang.oa.ws.pojo.ResultMassage;

public class MessageUtils {

    public static String getMessage(boolean isSystemMessage,String fromName,Object message){
        ResultMassage result = new ResultMassage();
        result.setSystem(isSystemMessage);
        result.setMessage(message);
        if(fromName != null) result.setFromName(fromName);
        return JSON.toJSONString(result);
    }

}
